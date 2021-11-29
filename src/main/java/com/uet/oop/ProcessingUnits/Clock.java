package com.uet.oop.ProcessingUnits;

import com.uet.oop.Entities.Bomb;
import com.uet.oop.Entities.Game;

public class Clock implements Runnable {
    private static Thread thread;
    private boolean timeUp;
    private int interval;
    private Game game;
    private Bomb bomb;

    public Clock(int _interval, Game game, Bomb bomb) {
        thread = new Thread(this, "CLOCK");
        this.timeUp = false;
        this.interval = _interval;
        this.game = game;
        this.bomb = bomb;
    }

    public boolean isTimeUp() {
        return timeUp;
    }

    public void setTimeUp(boolean timeUp) {
        this.timeUp = timeUp;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    @Override
    public void run() {
        while (!timeUp) {
            try {
                Thread.sleep(interval * 1000L);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            game.exploreAt(bomb.getCoordinatesX(), bomb.getCoordinatesY());
            interval = 0;
            timeUp = true;
        }
    }

    public void start() {
        if (!thread.isAlive()) {
            thread.start();
        }
    }
}
