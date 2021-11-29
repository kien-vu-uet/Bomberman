package com.uet.oop.Entities;

public class Bomb extends Piece {
    public static final int EXISTENCE_TIME = 3; // seconds
    public static final String IMAGE_PATH = "";
    public static final int RADIUS = 2;

    public Bomb() {

    }

    public Bomb(int coordinatesX, int coordinateY) {
        super(coordinatesX, coordinateY);
    }

    @Override
    public boolean canMove(Board board, int direction) {
        return false;
    }

    @Override
    public void move(int direction) {

    }

    @Override
    public String getSymbol() {
        return "(*)";
    }
}
