package com.uet.oop.Entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board {
    private static List<Piece> pieces;
    public static int size;

    public Board() {
        pieces = new ArrayList<>();
        size = 0;
    }

    void readBoard(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) System.err.println("File not found");
            Scanner sc = new Scanner(file);
            size = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < size && sc.hasNextLine(); i++) {
                String s = sc.nextLine();
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == '#') {
                        pieces.add(new Stone(i, j));
                    } else if (s.charAt(j) == '=') {
                        pieces.add(new Brick(i, j));
                    } else if (s.charAt(j) == '!') {
                        pieces.add(new Bot(i, j, new Random().nextInt(5)));
                    } else if (s.charAt(j) == '$') {
                        pieces.add(new Bomberman(i, j));
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
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

    public void removeAt(int x, int y) {
        Piece piece = getAt(x, y);
        pieces.remove(piece);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void add(Piece piece) {
        if (piece == null) return;
        pieces.add(piece);
    }

    public void print() {
        for (int i = 0; i < 30; i++) System.out.println();
        System.out.println(this);
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("\n");
        String[][] board = new String[size][size];
        for (Piece piece : pieces) {
            if (piece instanceof Bot) continue;
            board[piece.getCoordinatesX()][piece.getCoordinatesY()] = piece.getSymbol();
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) res.append(" ");
                else res.append(board[i][j]);
            }
            res.append("\n");
        }
        return res.toString();
    }

    public List<Piece> getBots() {
        List<Piece> bots = new ArrayList<>();
        for (Piece piece : pieces) {
            if (piece instanceof Bot) {
                bots.add(piece);
            }
        }
        return bots;
    }
}
