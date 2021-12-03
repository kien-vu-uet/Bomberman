package com.uet.oop.ProcessingUnits;

import com.uet.oop.Entities.Bomberman;
import com.uet.oop.Entities.Game;

public class PlayerController implements Runnable {
    private Thread thread;
    private boolean isRunning;
    private int direction;
    private Game game;
    private Bomberman bomberman;

    public PlayerController(Game game, Bomberman bomberman) {
        thread = new Thread(this, "PLAYER_CONTROLLER");
        isRunning = false;
        this.game = game;
        this.bomberman = bomberman;
    }

    @Override
    public void run() {
        while (isRunning) {
            if (direction == -1) continue;
            if (direction == 4) {
                game.bombAt(bomberman.getCoordinatesX(), bomberman.getCoordinatesY());
            }
            else {
                game.movePiece(bomberman, direction);
            }
            direction = -1;
        }
    }

    public void start() {
        isRunning = true;
        if (!thread.isAlive()) {
            thread.start();
        }
    }

    public void stop() {
        isRunning = false;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
