package com.uet.oop.Entities;

public class Game {
    private static Board board;

    public Game() {

    }

    public void initialize(String boardFile) {
        board = new Board();
        board.readBoard(boardFile);
    }

    public boolean validate(int x, int y) {
        return (x >= 1 && x < Board.size - 1 && y >= 1 && y < Board.size - 1);
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

    public void bombAt(int x, int y) {
        if (!validate(x, y)) return;
        Piece piece = board.getAt(x, y);
        if (piece instanceof Stone) return;
        if (piece instanceof Brick) return;
        board.add(new Bomb(x, y));
    }

    public void exploreAt(int x, int y) {
        if (!validate(x, y)) return;
        Piece piece;
        for (int i = x; i <= x + Bomb.RADIUS; i++) {
            if (i <= Board.size - 2 && (piece = board.getAt(i, y)) != null) {
                if (piece instanceof Stone) break;
                board.getPieces().remove(piece);
            }
        }
        for (int i = x - 1; i >= x - Bomb.RADIUS; i--) {
            if (i >= 1 && (piece = board.getAt(i, y)) != null) {
                if (piece instanceof Stone) break;
                board.getPieces().remove(piece);
            }
        }
        for (int j = y + 1; j <= y + Bomb.RADIUS; j++) {
            if (j <= Board.size - 2 && (piece = board.getAt(x, j)) != null) {
                if (piece instanceof Stone) break;
                board.getPieces().remove(piece);
            }
        }
        for (int j = y - 1; j >= y - Bomb.RADIUS; j--) {
            if (j >= 1 && (piece = board.getAt(x, j)) != null) {
                if (piece instanceof Stone) break;
                board.getPieces().remove(piece);
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board newBoard) {
        board = newBoard;
    }
}
