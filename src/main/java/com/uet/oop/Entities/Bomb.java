package com.uet.oop.Entities;

import javafx.scene.image.Image;

import java.io.File;

public class Bomb extends Piece {
    public static final int RADIUS = 2;
    private long startTime;
    private Image bombImage;
    private Image explorationImage;

    public Bomb() {
        loadImages();
    }

    public Bomb(int coordinatesX, int coordinateY) {
        super(coordinatesX, coordinateY);
        loadImages();
    }

    public Image getBombImage() {
        return bombImage;
    }

    public Image getExplorationImage() {
        return explorationImage;
    }

    private void loadImages() {
        bombImage = new Image(new File("src//main//resources//com//uet//oop//Images//Bomb//0.gif").toURI().toString());
        explorationImage = new Image(new File("src//main//resources//com//uet//oop//Images//BombExplores//0.gif").toURI().toString());
    }

    public long getStartTime() {
        return startTime;
    }

    public void startCountingDown() {
        this.startTime = System.nanoTime();
    }

    public boolean isTimingUp() {
        return (System.nanoTime() - startTime == 3e9);
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
