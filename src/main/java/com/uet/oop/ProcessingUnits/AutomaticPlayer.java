package com.uet.oop.ProcessingUnits;

import com.uet.oop.Entities.Bomberman;
import com.uet.oop.Entities.Game;

import java.util.Random;

public class AutomaticPlayer implements Runnable {
    private Thread thread;
    private boolean isRunning;
    private Game game;
    private Bomberman bomberman;

    public AutomaticPlayer(Game game, Bomberman bomberman) {
        thread = new Thread(this, "AUTOMATIC_PLAYER");
        isRunning = false;
        this.game = game;
        this.bomberman = bomberman;
    }

    @Override
    public void run() {
        while (isRunning) {
            Random random = new Random();
            int direction = random.nextInt(4) % 4;
            if (bomberman.canMove(game.getBoard(), direction)) {
                game.movePiece(bomberman, direction);
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                game.bombAt(bomberman.getCoordinatesX(), bomberman.getCoordinatesY());
            }
        }
    }
}
