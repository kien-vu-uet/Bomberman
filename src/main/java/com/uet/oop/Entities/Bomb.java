package com.uet.oop.Entities;

import javafx.scene.image.Image;

import java.io.File;

public class Bomb extends Piece {
    public static final int RADIUS = 2;
    private long startTime;
    public static final Image bomb
            = new Image(new File("src//main//resources//com//uet//oop//Images//Bomb//0.gif").toURI().toString());
    public static final Image exploration
            = new Image(new File("src//main//resources//com//uet//oop//Images//BombExplores//0.gif").toURI().toString());

    public Bomb() {

    }

    public Bomb(int coordinatesX, int coordinateY) {
        super(coordinatesX, coordinateY);
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
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
        return "*";
    }
}
