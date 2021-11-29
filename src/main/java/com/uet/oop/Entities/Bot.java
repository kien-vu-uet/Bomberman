package com.uet.oop.Entities;

public class Bot extends Piece {
    public static final String IMAGE_PATH = "";
    private int level;

    public Bot() {

    }

    public Bot(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
        setLevel(1);
    }

    public Bot(int coordinatesX, int coordinatesY, int level) {
        super(coordinatesX, coordinatesY);
        setLevel(level);
    }

    public int getLevel() {
        return level;
    }

    /**
     * Set level and speed for Bot
     * * Speed increases depending on level
     */
    public void setLevel(int level) {
        this.level = level;
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
     * Symbol of thread is "BOT"
     */
    @Override
    public String getSymbol() {
        return "<!>";
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
