package com.uet.oop.Entities;

public class Brick extends Piece {
    public static final String IMAGE_PATH = "";
    private boolean isBroken;

    public Brick() {

    }

    public Brick(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
        this.isBroken = false;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void breakBrick() {
        isBroken = true;
    }

    /**
     * Brick is static, it cannot move
     */
    @Override
    public boolean canMove(Board board, int direction) {
        return false;
    }

    @Override
    public String getSymbol() {
        return "=";
    }

    @Override
    public void move(int direction) {

    }
}
