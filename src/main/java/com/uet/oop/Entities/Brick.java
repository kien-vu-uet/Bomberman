package com.uet.oop.Entities;

import javafx.scene.image.Image;

import java.io.File;

public class Brick extends Piece {
    private boolean isBroken;
    private Image standingImage;
    private Image explorationImage;

    public Brick() {
        loadImages();
    }

    public Brick(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
        this.isBroken = false;
        loadImages();
    }

    private void loadImages() {
        standingImage
                = new Image(new File("src//main//resources//com//uet//oop//Images//BrickFires//1.png").toURI().toString());
        explorationImage
                = new Image(new File("src//main//resources//com//uet//oop//Images//BrickFires//1.gif").toURI().toString());
    }

    public Image getStandingImage() {
        return standingImage;
    }

    public Image getExplorationImage() {
        return explorationImage;
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
