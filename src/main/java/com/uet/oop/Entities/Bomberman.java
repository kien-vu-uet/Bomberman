package com.uet.oop.Entities;

public class Bomberman extends Piece {
    public static final String IMAGE_PATH = "";

    public Bomberman() {

    }

    public Bomberman(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
    }

    @Override
    public boolean canMove(Board board, int direction) {
        int x = super.getCoordinatesX();
        int y = super.getCoordinatesY();
        switch (direction) {
            case (0) -> y -= 1;
            case (1) -> y += 1;
            case (2) -> x -= 1;
            case (3) -> x += 1;
        }
        Piece piece;
        if ((piece = board.getAt(x, y)) == null) return true;
        if (piece instanceof Stone || piece instanceof Brick) return false;
        return true;
    }

    /**
     * Symbol of bomberman is "B"
     */
    @Override
    public String getSymbol() {
        return "<^>";
    }

    @Override
    public void move(int direction) {
        int x = super.getCoordinatesX();
        int y = super.getCoordinatesY();
        switch (direction) {
            case (0) -> super.setCoordinatesY(y - 1);
            case (1) -> super.setCoordinatesY(y + 1);
            case (2) -> super.setCoordinatesX(x - 1);
            case (3) -> super.setCoordinatesX(x + 1);
        }
    }
}
