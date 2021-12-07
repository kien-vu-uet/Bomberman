package com.uet.oop.GraphicsControllers;

import com.uet.oop.BombermanGame;
import com.uet.oop.Entities.*;
import com.uet.oop.ProcessingUnits.MusicPlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    public static final int SIZE = 30;

    private MusicPlayer musicPlayer;
    private MusicPlayer selectSound;
    private MusicPlayer moveSound;
    private MusicPlayer explosionSound;
    private Game game;
    private List<ImageView> imageViews;
    private ImageView bombermanImgView;
    private Bomberman bomberman;
    public static boolean isRunning;
    public static boolean paused;

    @FXML private Scene settingScene;
    @FXML private Scene playingScene;
    @FXML private Stage stage;
    @FXML private Pane pane;
    @FXML private Label NoBombs;
    @FXML private Label healthPoint;

    @FXML private ImageView musicIcon;
    @FXML private ImageView soundIcon;
    @FXML private Slider musicSlider;
    @FXML private Slider soundSlider;
    @FXML private ImageView loading;
    @FXML private Pane gamePane;

    @FXML
    private void musicSliderOnDragged() {
        selectSound.play();
        double value = musicSlider.getValue();
        if (value == 0) {
            musicIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//muteMusic.png").toURI().toString()));
            musicPlayer.setVolume(0);
        } else {
            musicIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//music.png").toURI().toString()));
            musicPlayer.setVolume(value);
        }
    }

    @FXML
    private void muteMusic() {
        selectSound.play();
        if (musicSlider.getValue() > 0) {
            musicIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//muteMusic.png").toURI().toString()));
            musicSlider.setValue(0);
            musicPlayer.setVolume(0);
        } else {
            musicIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//music.png").toURI().toString()));
            musicSlider.setValue(1);
            musicPlayer.setVolume(1);
        }
    }

    @FXML
    private void soundSliderOnDragged() {
        selectSound.play();
        double value = soundSlider.getValue();
        if (value == 0) {
            soundIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//muteSound.png").toURI().toString()));
            selectSound.setVolume(0);
            moveSound.setVolume(0);
            explosionSound.setVolume(0);
        } else {
            soundIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//sound.png").toURI().toString()));
            selectSound.setVolume(value);
            moveSound.setVolume(value);
            explosionSound.setVolume(value);
        }
    }

    @FXML
    private void muteSound() {
        selectSound.play();
        if (soundSlider.getValue() > 0) {
            soundIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//muteSound.png").toURI().toString()));
            soundSlider.setValue(0);
            selectSound.setVolume(0);
            moveSound.setVolume(0);
            explosionSound.setVolume(0);
        } else {
            soundIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//sound.png").toURI().toString()));
            soundSlider.setValue(1);
            selectSound.setVolume(1);
            moveSound.setVolume(1);
            explosionSound.setVolume(1);
        }
    }

    @FXML
    private void resumeGame() {
        selectSound.play();
        if (isRunning) {
            paused = false;
            stage.setScene(playingScene);
        }
    }

    @FXML
    private void exitGame() {
        selectSound.play();
        isRunning = false;

    }

    @FXML
    private void mouseMoved() {
        moveSound.play();
    }

    public GameController(Game game, Bomberman bomberman) {
        this.game = game;
        this.bomberman = bomberman;
    }


    public void show(double musicVolume, double soundVolume) {
        musicPlayer = new MusicPlayer("src//main//resources//com//uet//oop//Musics//Battle.mp3", true);
        musicPlayer.setVolume(0.1);
        musicPlayer.play();
        for (double i = 0.1; i <= musicVolume; i += 0.1) {
            musicPlayer.setVolume(i);
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("src//main//resources//com//uet//oop//FXML//InGame.fxml"));
            settingScene = new Scene(fxmlLoader.load(), 720, 540);
            stage = BombermanGame.mainStage;
            stage.setScene(settingScene);
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        selectSound = new MusicPlayer("src//main//resources//com//uet//oop//Sounds//Select.wav", false, soundVolume);
        moveSound = new MusicPlayer("src//main//resources//com//uet//oop//Sounds//Move.wav", false, soundVolume);
        explosionSound = new MusicPlayer("src//main//resources//com//uet//oop//Sounds//Explosion.mp3", false, soundVolume);

        pane = new Pane();
        pane.setPrefSize(540, 540);
        pane.setLayoutX(180);
        pane.setLayoutY(0);
        pane.setStyle("-fx-background-color: #32cd32");

        for (Piece piece : game.getBoard().getPieces()) {
            int x = piece.getCoordinatesX() * SIZE;
            int y = piece.getCoordinatesY() * SIZE;
            ImageView imgview = new ImageView();
            imgview.setPreserveRatio(true);
            if (piece instanceof Bomberman) {
                imgview.setImage(((Bomberman) piece).getStandingImage(3));
                imgview.setFitWidth(20);
                imgview.setLayoutX(x + 5);
                imgview.setLayoutY(y);
                bombermanImgView = imgview;
            } else if (piece instanceof Bot) {
                imgview.setImage(((Bot) piece).getMoveImage());
                imgview.setFitWidth(SIZE);
                imgview.setLayoutX(x);
                imgview.setLayoutY(y);
            } else if (piece instanceof Brick) {
                imgview.setImage(((Brick) piece).getStandingImage());
                imgview.setFitWidth(SIZE);
                imgview.setLayoutX(x);
                imgview.setLayoutY(y);
            } else if (piece instanceof Stone) {
                imgview.setImage(((Stone) piece).getStandingImage());
                imgview.setFitWidth(SIZE);
                imgview.setLayoutX(x);
                imgview.setLayoutY(y);
            } else if (piece instanceof Bomb) {
                imgview.setImage(((Bomb) piece).getBombImage());
                imgview.setFitWidth(SIZE);
                imgview.setLayoutX(x);
                imgview.setLayoutY(y);
            }
            imageViews.add(imgview);
        }
        pane.getChildren().addAll(imageViews);
        gamePane = pane;

        ImageView leftside = new ImageView();
        leftside.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//LeftSide.gif").toURI().toString()));
        leftside.setPreserveRatio(true);
        leftside.setLayoutX(0);
        leftside.setLayoutY(0);

        NoBombs = new Label(String.valueOf(bomberman.getNumOfBombs()));
        NoBombs.setFont(new Font(48));
        NoBombs.setLayoutX(125);
        NoBombs.setLayoutY(235);

        healthPoint = new Label(String.valueOf(bomberman.getHealthPoint()));
        healthPoint.setFont(new Font(48));
        healthPoint.setLayoutX(125);
        healthPoint.setLayoutY(298);

        Pane root = new Pane();
        root.setPrefSize(720, 540);
        root.getChildren().addAll(leftside, pane, NoBombs, healthPoint);
        playingScene = new Scene(root, 720, 540);
        playingScene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            switch (keyEvent.getCode()) {
                case ESCAPE -> stage.setScene(settingScene);
                case A -> moveBomberman(0);
                case D -> moveBomberman(1);
                case W -> moveBomberman(2);
                case S -> moveBomberman(3);
                case SPACE -> bomb();
            }
            NoBombs.setText(String.valueOf(bomberman.getNumOfBombs()));
            healthPoint.setText(String.valueOf(bomberman.getHealthPoint()));
        });

        loading.setVisible(false);
    }

    public void bomb() {
        if (bomberman.getNumOfBombs() < 1) return;
        int x = bomberman.getCoordinatesX();
        int y = bomberman.getCoordinatesY();
        Bomb bomb = game.bombAt(x, y);
        bomberman.useBomb();
        ImageView bombview = new ImageView(bomb.getBombImage());
        bombview.setFitWidth(SIZE);
        bombview.setPreserveRatio(true);
        bombview.setLayoutX(bomb.getCoordinatesY());
        bombview.setLayoutY(bomb.getCoordinatesX());
        imageViews.add(bombview);
        pane.getChildren().add(bombview);
    }

    public void explore(List<Piece> deadPieces) {
        List<Integer> index = new ArrayList<>();
        for (Piece piece : deadPieces) {
            int i = game.getBoard().getPieces().indexOf(piece);
            index.add(i);
            if (piece instanceof Brick) {
                imageViews.get(i).setImage(((Brick) piece).getExplorationImage());
            } else if (piece instanceof Bot) {
                imageViews.get(i).setImage(((Bot) piece).getDeadImage());
            } else if (piece instanceof Bomb) {
                imageViews.get(i).setImage(((Bomb) piece).getExplorationImage());
            }
        }
        sleep(100);
        Image img = new Bomb().getExplorationImage();
        for (int i : index) {
            imageViews.get(i).setImage(img);
        }
        sleep(100);
        for (int i : index) {
            imageViews.remove(i);
//            pane.getChildren().remove(i);
        }
    }

    private void sleep(int interval) {
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveBomberman(int direction) {
        // 0 -> left; 1 -> right; 2 -> up; 3 -> down
        bombermanImgView.setImage(bomberman.getMovingImage(direction));
        if (!bomberman.canMove(game.getBoard(), direction)) {
            bombermanImgView.setImage(bomberman.getStandingImage(direction));
        } else {
            double x = bombermanImgView.getLayoutX();
            double y = bombermanImgView.getLayoutY();
            switch (direction) {
                case (0) -> {
                    bombermanImgView.setLayoutX(x - 10);
                    bombermanImgView.setLayoutX(x - 20);
                    bombermanImgView.setLayoutX(x - 30);
                }
                case (1) -> {
                    bombermanImgView.setLayoutX(x + 10);
                    bombermanImgView.setLayoutX(x + 20);
                    bombermanImgView.setLayoutX(x + 30);
                }
                case (2) -> {
                    bombermanImgView.setLayoutY(y - 10);
                    bombermanImgView.setLayoutY(y - 20);
                    bombermanImgView.setLayoutY(y - 30);
                }
                case (3) -> {
                    bombermanImgView.setLayoutY(y + 10);
                    bombermanImgView.setLayoutY(y + 20);
                    bombermanImgView.setLayoutY(y + 30);
                }
            }
        }
        game.movePiece(bomberman, direction);
    }

    public void bombermanDie() {
        bombermanImgView.setImage(bomberman.getExploringImage());
        //
    }

    public void moveBot(Bot bot, int direction) {
        if (!bot.canMove(game.getBoard(), direction)) return;
        ImageView imgview = imageViews.get(game.getBoard().getPieces().indexOf(bot));
        double x = imgview.getLayoutX();
        double y = imgview.getLayoutY();
        switch (direction) {
            case (0) -> {
                imgview.setLayoutX(x - 10);
                imgview.setLayoutX(x - 20);
                imgview.setLayoutX(x - 30);
            }
            case (1) -> {
                imgview.setLayoutX(x + 10);
                imgview.setLayoutX(x + 20);
                imgview.setLayoutX(x + 30);
            }
            case (2) -> {
                imgview.setLayoutY(y - 10);
                imgview.setLayoutY(y - 20);
                imgview.setLayoutY(y - 30);
            }
            case (3) -> {
                imgview.setLayoutY(y + 10);
                imgview.setLayoutY(y + 20);
                imgview.setLayoutY(y + 30);
            }
        }
    }
}
