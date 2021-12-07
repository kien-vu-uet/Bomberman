package com.uet.oop.Entities;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Bomberman extends Piece {
    public static final int WIDTH = 18;
    private List<Image> standingImages;
    private List<Image> movingImages;
    private String color;
    private int numOfBombs;
    private int healthPoint;
    private boolean isAlive;

    public Bomberman(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
        setColor("Yellow");
        numOfBombs = 1;
        healthPoint = 1;
        isAlive = true;
    }
    public Bomberman(int coordinatesX, int coordinatesY, String color) {
        super(coordinatesX, coordinatesY);
        setColor(color);
        numOfBombs = 1;
        healthPoint = 1;
        isAlive = true;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        loadImages();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getNumOfBombs() {
        return numOfBombs;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void useBomb() {
        numOfBombs--;
    }

    public void equipBomb() {
        numOfBombs++;
    }

    public void bleed() {
        healthPoint--;
        if (healthPoint <= 0) isAlive = false;
    }

    public void heal() {
        healthPoint++;
    }

    private void loadImages() {
        String path = "src//main//resources//com//uet//oop//Images//Bomberman//" + color;
        standingImages = new ArrayList<>();
        standingImages.add(new Image(new File(path + "//00.gif").toURI().toString()));
        standingImages.add(new Image(new File(path + "//11.gif").toURI().toString()));
        standingImages.add(new Image(new File(path + "//22.gif").toURI().toString()));
        standingImages.add(new Image(new File(path + "//33.gif").toURI().toString()));
        //
        movingImages = new ArrayList<>();
        movingImages.add(new Image(new File(path + "//0.gif").toURI().toString()));
        movingImages.add(new Image(new File(path + "//1.gif").toURI().toString()));
        movingImages.add(new Image(new File(path + "//2.gif").toURI().toString()));
        movingImages.add(new Image(new File(path + "//3.gif").toURI().toString()));
        //
        movingImages.add(new Image(new File(path + "//4.gif").toURI().toString()));
    }

    public Image getStandingImage(int direction) {
        return standingImages.get(direction);
    }

    public Image getMovingImage(int direction) {
        return movingImages.get(direction);
    }

    public Image getExploringImage() {
        return movingImages.get(4);
    }

    @Override
    public boolean canMove(Board board, int direction) {
        int x = super.getCoordinatesX();
        int y = super.getCoordinatesY();
        switch (direction) {
            case (2) -> y = y - 1;
            case (3) -> y = y + 1;
            case (0) -> x = x - 1;
            case (1) -> x = x + 1;
        }
        Piece piece;
        if ((piece = board.getAt(x, y)) == null) return true;
        return !(piece instanceof Stone) && !(piece instanceof Brick) && !(piece instanceof Bomb);
    }

    /**
     * Symbol of bomberman is "B"
     */
    @Override
    public String getSymbol() {
        return "$";
    }

    @Override
    public void move(int direction) {
        int x = super.getCoordinatesX();
        int y = super.getCoordinatesY();
        switch (direction) {
            case (2) -> super.setCoordinatesY(y - 1);
            case (3) -> super.setCoordinatesY(y + 1);
            case (0) -> super.setCoordinatesX(x - 1);
            case (1) -> super.setCoordinatesX(x + 1);
        }
    }
}
