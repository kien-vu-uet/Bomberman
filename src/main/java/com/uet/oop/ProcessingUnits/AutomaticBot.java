package com.uet.oop.ProcessingUnits;

import com.uet.oop.BombermanCommandLine;
import com.uet.oop.Entities.Bot;
import com.uet.oop.Entities.Game;

import java.util.Random;

public class AutomaticBot implements Runnable {
    private Thread thread;
    private boolean isRunning;
    private Game game;
    private Bot bot;

    public AutomaticBot(Game game, Bot bot) {
        thread = new Thread(this, "BOT_CONTROLLER");
        isRunning = false;
        this.game = game;
        this.bot = bot;
    }

    @Override
    public void run() {
        while (isRunning) {
            Random random = new Random();
            int direction; //= random.nextInt(4) % 2;
            if (random.nextInt(2) == 1) direction = 0;
            else direction = 2;
            game.movePiece(bot, direction);
            game.getBoard().print();
            try {
                int interval = random.nextInt(3000 / bot.getLevel());
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
}
