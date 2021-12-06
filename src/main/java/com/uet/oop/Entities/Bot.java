package com.uet.oop.Entities;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Bot extends Piece {
    private int level;
    private Image moveImages;
    private Image deadImages;

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
        moveImages = new Image(new File("src//main//resources//com//uet//oop//Images//Bot//" + p + ".gif").toURI().toString());
        //
        deadImages = new Image(new File("src//main//resources//com//uet//oop//Images//Bot//" + p + p + ".gif").toURI().toString());
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
            case (0) -> y -= 1;
            case (1) -> y += 1;
            case (2) -> x -= 1;
            case (3) -> x += 1;
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
            case (0) -> super.setCoordinatesY(y - 1);
            case (1) -> super.setCoordinatesY(y + 1);
            case (2) -> super.setCoordinatesX(x - 1);
            case (3) -> super.setCoordinatesX(x + 1);
        }
    }
}
