package com.uet.oop.Entities;

public class Stone extends Piece {
    public static final String IMAGE_PATH = "";

    public Stone() {

    }

    public Stone(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
    }

    /**
     * Stone is static, it cannot move
     */
    @Override
    public boolean canMove(Board board, int direction) {
        return false;
    }

    @Override
    public String getSymbol() {
        return "#";
    }

    @Override
    public void move(int direction) {

    }
}
