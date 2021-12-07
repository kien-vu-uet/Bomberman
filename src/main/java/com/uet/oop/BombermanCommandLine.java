package com.uet.oop;

import com.uet.oop.Entities.Bot;
import com.uet.oop.Entities.Game;
import com.uet.oop.Entities.Piece;
import com.uet.oop.ProcessingUnits.AutomaticBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BombermanCommandLine {
    public static boolean signal = false;

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.initialize("src/main/resources/com/uet/oop/maps/map1.txt");
        game.getBoard().print();
        game.bombAt(2, 12);
        game.getBoard().print();
//        List<Piece> bots = game.getBoard().getBots();
//        List<AutomaticBot> autobots = new ArrayList<>();
//        for (Piece piece : bots) {
//            autobots.add(new AutomaticBot(game, (Bot) piece));
//        }
//        for (AutomaticBot autobot : autobots) {
//            autobot.start();
//        }
//        Bot bot = new Bot(5, 5, 2);
//        game.getBoard().add(bot);
//        AutomaticBot autobot = new AutomaticBot(game, bot);
//        autobot.start();
//        while (true) {
//            Random random = new Random();
//            Thread.sleep(random.nextInt(1500));
////            int x = random.nextInt(16);
////            int y = random.nextInt(16);
//            game.exploreAt(1, 1);
////            System.out.println(x + "," + y);
//        }
    }

    public static void setSignal() {
        signal = true;
    }
}
