package com.uet.oop.Entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board {
    private static List<Piece> pieces;
    public static int length;
    private int playingTime;

    public Board() {
        pieces = new ArrayList<>();
        length = 0;
    }

    void readBoard(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) System.err.println("File not found");
            Scanner sc = new Scanner(file);
            playingTime = sc.nextInt();
            length = sc.nextInt();
            System.out.println(playingTime + "\n" + length);
            sc.nextLine();
            for (int j = 0; j < length && sc.hasNextLine(); j++) {
                String s = sc.nextLine();
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '#') {
                        pieces.add(new Stone(i, j));
                    } else if (s.charAt(i) == '=') {
                        pieces.add(new Brick(i, j));
                    } else if (s.charAt(i) == '!') {
                        pieces.add(new Bot(i, j, new Random().nextInt(5) + 1));
                    } else if (s.charAt(i) == '$') {
                        pieces.add(new Bomberman(i, j));
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setPlayingTime(int time) {
        playingTime += time;
    }

    public double getPlayingTime() {
        return this.playingTime;
    }

    public Piece getAt(int x, int y) {
        if (pieces.isEmpty()) return null;
        for (Piece piece : pieces) {
            if (piece != null && piece.checkPosition(x, y)) {
                return piece;
            }
        }
        return null;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void add(Piece piece) {
        if (piece == null) return;
        pieces.add(piece);
    }

    public List<Piece> getAllAt(int x, int y) {
        List<Piece> res = new ArrayList<>();
        Piece piece;
        for (int i = 0; i < pieces.size(); i++) {
            if ((piece = pieces.get(i)) != null && piece.checkPosition(x, y)) res.add(piece);
        }
        return res;
    }

    public void remove(Piece piece) {
        if (piece instanceof Bomberman) return;
        if (piece instanceof Bot b1) pieces.add(b1.getContainedBonus());
        else if (piece instanceof Brick b2) pieces.add(b2.getContainedBonus());
        pieces.remove(piece);
    }

    public void print() {
        for (int i = 0; i < 30; i++) System.out.println();
        System.out.println(this);
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("\n");
        String[][] board = new String[length][length];
        for (Piece piece : pieces) {
            if (piece instanceof Bot) continue;
            board[piece.getCoordinatesY()][piece.getCoordinatesX()] = piece.getSymbol();
        }
        for (int j = 0; j < length; j++) {
            for (int i = 0; i < length; i++) {
                if (board[j][i] == null) res.append(" ");
                else res.append(board[j][i]);
            }
            res.append("\n");
        }
        return res.toString();
    }

    public List<Bot> getBots() {
        List<Bot> bots = new ArrayList<>();
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            if (piece instanceof Bot) {
                bots.add((Bot) piece);
            }
        }
        return bots;
    }

    public Bomberman getBomberman() {
        for (Piece piece : pieces) {
            if (piece instanceof Bomberman) {
                return (Bomberman) piece;
            }
        }
        return null;
    }

    public List<Bomb> getBombs() {
        List<Bomb> bombs = new ArrayList<>();
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            if (piece instanceof Bomb) {
                bombs.add((Bomb) piece);
            }
        }
        return bombs;
    }
}
