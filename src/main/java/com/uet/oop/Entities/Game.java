package com.uet.oop.Entities;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Board board;

    public Game() {

    }

    public void initialize(String boardFile) {
        board = new Board();
        board.readBoard(boardFile);
    }

    public boolean validate(int x, int y) {
        return (x >= 1 && x < Board.length - 1 && y >= 1 && y < Board.length - 1);
    }

    public void movePiece(Piece piece, int direction) {
        if (piece == null) return;
        if (piece instanceof Stone) return;
        if (piece instanceof Brick) return;
        if (piece instanceof Bomb) return;
        if (piece.canMove(board, direction)) {
            piece.move(direction);
        }
    }

    public Bomb bombAt(int x, int y) {
        if (!validate(x, y)) return null;
        Piece piece = board.getAt(x, y);
        if (piece instanceof Stone) return null;
        if (piece instanceof Brick) return null;
        Bomb bomb = new Bomb(x, y);
        board.add(bomb);
        bomb.startCountingDown();
        return bomb;
    }

    public void exploreAt(int x, int y) {
        for (Piece piece : getPiecesInRange(x, y)) {
            if (piece instanceof Bomberman bomberman) {
                bomberman.bleed();
                if (bomberman.isAlive()) continue;
            }
            board.getPieces().remove(piece);
        }
    }

    public List<Piece> getPiecesInRange(int x, int y) {
        if (!validate(x, y)) return new ArrayList<>();
        List<Piece> res = new ArrayList<>();
        Piece piece;
        for (int i = x + 1; i <= x + Bomb.RADIUS; i++) {
            if (i <= Board.length - 2 && (piece = board.getAt(i, y)) != null) {
                if (piece instanceof Stone) break;
                res.add(piece);
            }
        }
        for (int i = x - 1; i >= x - Bomb.RADIUS; i--) {
            if (i >= 1 && (piece = board.getAt(i, y)) != null) {
                if (piece instanceof Stone) break;
                res.add(piece);
            }
        }
        for (int j = y + 1; j <= y + Bomb.RADIUS; j++) {
            if (j <= Board.length - 2 && (piece = board.getAt(x, j)) != null) {
                if (piece instanceof Stone) break;
                res.add(piece);
            }
        }
        for (int j = y - 1; j >= y - Bomb.RADIUS; j--) {
            if (j >= 1 && (piece = board.getAt(x, j)) != null) {
                if (piece instanceof Stone) break;
                res.add(piece);
            }
        }

        for (Piece p : board.getPieces()) {
            if (p.checkPosition(x, y)) res.add(p);
        }

        return res;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board newBoard) {
        board = newBoard;
    }
}
