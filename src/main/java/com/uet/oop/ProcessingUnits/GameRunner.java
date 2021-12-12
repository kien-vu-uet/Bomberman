package com.uet.oop.ProcessingUnits;

import com.uet.oop.Entities.Bomb;
import com.uet.oop.Entities.Bonus;
import com.uet.oop.Entities.Bot;
import com.uet.oop.Entities.Piece;
import com.uet.oop.GraphicsControllers.GameController;
import com.uet.oop.GraphicsControllers.HomeController;


import java.util.List;
import java.util.Random;

public class GameRunner implements Runnable {
    private Thread thread;
    private GameController gc;

    public GameRunner(GameController gc) {
        thread = new Thread(this, "RUNNING_GAME");
        thread.setDaemon(true);
        this.gc = gc;
    }

    @Override
    public void run() {
        gc.setLoadingDone();
        gc.game.run();
        while (gc.bomberman.isAlive() || gc.game.isRunning()) { // fix
            if (gc.game.isPaused()) continue;
            gc.setRemainingTime(gc.game.getRemainingTime());
            // bombs explore
            List<Bomb> bombs = gc.game.getBoard().getBombs();
            if (!bombs.isEmpty()) {
                bombs.stream().filter(Bomb::isTimedOut).forEach(bomb -> {
                    gc.explore(gc.game.explore(bomb));
                });
            }
            // check
            List<Piece> pieces = gc.game.getBoard().getAllAt(gc.bomberman.getCoordinatesX(), gc.bomberman.getCoordinatesY());
            if (!pieces.isEmpty() && !gc.bomberman.isStunned()) {
                pieces.forEach(p -> {
                    if (p instanceof Bot) gc.bomberman.bleed();
                    else if (p instanceof Bonus b) {
                        switch (b.getType()) {
                            case (0) -> gc.bomberman.equipBomb();
                            case (1) -> gc.bomberman.heal();
                            case (2) -> gc.game.bonusTime();
                        }
                        gc.claimBonus(b);
                    }
                });
            }
            if (!gc.bomberman.isAlive()) {
                gc.bombermanDie();
                gc.game.setStatus(0);
                continue;
            } else if (gc.bomberman.isStunned()) gc.fade();
            gc.bomberman.restoreBomb();
            // bots move
            Random random = new Random();
            if (!gc.bots.isEmpty()) {
                gc.bots.forEach(bot -> {
                    int direction = random.nextInt(4) % 4;
                    gc.moveBot(bot, direction);
                });
            } else gc.game.setStatus(1);
        }
        if (gc.game.getEndingStatus() == 1) {
            if (HomeController.SELECTED_LEVEL > HomeController.HIGHEST_LEVEL) HomeController.HIGHEST_LEVEL++;
            gc.setEnding(true);
        }
        else if (gc.game.getEndingStatus() == 0) gc.setEnding(false);
    }

    public void start() {
        if (thread.isAlive()) return;
        thread.start();
    }
}
