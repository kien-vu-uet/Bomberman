package com.uet.oop;

import com.uet.oop.Entities.Game;

import java.util.Scanner;

public class BombermanCommandLine {
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize("src/main/resources/com/uet/oop/maps/map1.txt");
        Scanner sc = new Scanner(System.in);
        game.getBoard().print();
        System.out.print("(x, y) = ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        game.exploreAt(x, y);
        game.getBoard().print();
//        while (true) {
//            game.getBoard().print();
//            int op, x, y;
//            System.out.print("(x,y) = ");
//            x = sc.nextInt();
//            y = sc.nextInt();
//            Piece piece = game.getBoard().getAt(x, y);
//            if (piece == null) {
//                System.out.println("Not a piece!");
//                continue;
//            }
//            System.out.println("Using " + piece.getClass().getName());
//            System.out.print("Direction: 0 - left | 1 -> right | 2 -> up | 3 -> down\ndirection = ");
//            op = sc.nextInt();
//            if (op >= 0 && op <= 3) {
//                game.movePiece(piece, op);
//                System.out.println("Move to " + piece.getCoordinatesX() + "," + piece.getCoordinatesY());
//            } else {
//                System.out.println("Exit");
//                break;
//            }
//        }
        sc.close();
    }
}
