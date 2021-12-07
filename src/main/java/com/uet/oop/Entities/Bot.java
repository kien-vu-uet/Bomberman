package com.uet.oop.Entities;

import javafx.scene.image.Image;

import java.io.File;

public class Bot extends Piece {
    private int level;
    private Image moveImage;
    private Image deadImage;

    public Bot(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
        setLevel(1);
    }

    public Bot(int coordinatesX, int coordinatesY, int level) {
        super(coordinatesX, coordinatesY);
        setLevel(level);
    }

    private void loadImages() {
        String p = String.valueOf(level - 1);
        moveImage = new Image(new File("src//main//resources//com//uet//oop//Images//Bot//" + p + ".gif").toURI().toString());
        //
        deadImage = new Image(new File("src//main//resources//com//uet//oop//Images//Bot//" + p + p + ".gif").toURI().toString());
    }

    public Image getMoveImage() {
        return moveImage;
    }

    public Image getDeadImage() {
        return deadImage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        loadImages();
    }

    @Override
    public boolean canMove(Board board, int direction) {
        int x = super.getCoordinatesX();
        int y = super.getCoordinatesY();
        switch (direction) {
            case (2) -> y -= 1;
            case (3) -> y += 1;
            case (0) -> x -= 1;
            case (1) -> x += 1;
        }
        Piece piece;
        if ((piece = board.getAt(x, y)) == null) return true;
        return !(piece instanceof Stone) && !(piece instanceof Brick) && !(piece instanceof Bomb);
    }

    /**
     * Symbol of thread is "BOT"
     */
    @Override
    public String getSymbol() {
        return "!";
    }

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
