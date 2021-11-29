package com.uet.oop.ProcessingUnits;

public class PlayerListener implements Runnable {
    private Thread thread;
    private boolean isRunning;

    public PlayerListener() {
        thread = new Thread(this, "PLAYERLISTENER");
        isRunning = false;
    }

    @Override
    public void run() {

    }

    public void start() {

    }

    public void stop() {

    }
}
