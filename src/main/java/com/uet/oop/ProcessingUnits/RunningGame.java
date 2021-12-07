package com.uet.oop.ProcessingUnits;

import com.uet.oop.Entities.*;
import com.uet.oop.GraphicsControllers.GameController;

import java.util.List;
import java.util.Random;

public class RunningGame implements Runnable {
    private Thread thread;
    private GameController gc;
    private Game game;
    private Bomberman bomberman;

    public RunningGame(Game game, GameController gc, Bomberman bomberman) {
        thread = new Thread(this, "RUNNING_GAME");
        this.bomberman = bomberman;
        this.game = game;
        this.gc = gc;
    }

    @Override
    public void run() {
        while (bomberman.isAlive()) {
            // bombs explore
            List<Bomb> bombs = game.getBoard().getBombs();
            for (Bomb bomb : bombs) {
                if (bomb.isTimingUp()) {
                    List<Piece> deadPieces = game.getPiecesInRange(bomb.getCoordinatesX(), bomb.getCoordinatesY());
                    gc.explore(deadPieces);
                    game.exploreAt(bomb.getCoordinatesX(), bomb.getCoordinatesY());
                    if (!bomberman.isAlive()) gc.bombermanDie();
                }
            }
            //
            if (game.getBoard().getAt(bomberman.getCoordinatesX(), bomberman.getCoordinatesY()) instanceof Bot) {
                bomberman.bleed();
                gc.bombermanDie();
            }
            // bots move
            List<Bot> bots = game.getBoard().getBots();
            Random random = new Random();
            for (Bot bot : bots) {
                if (random.nextInt(2) == 1) continue;
                int direction = random.nextInt(4) % 4;
                gc.moveBot(bot, direction);
                game.movePiece(bot, direction);
            }
        }
    }

    public void start() {
        if (thread.isAlive()) return;
        thread.start();
    }
}
