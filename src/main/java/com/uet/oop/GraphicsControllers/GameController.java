package com.uet.oop.GraphicsControllers;

import com.uet.oop.BombermanGame;
import com.uet.oop.ProcessingUnits.MusicPlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

public class GameController {
    private MusicPlayer musicPlayer;
    private MusicPlayer selectSound;
    private MusicPlayer moveSound;
    private MusicPlayer explosionSound;
    private boolean isRunning;
    private boolean isPausing;

    @FXML private ImageView musicIcon;
    @FXML private ImageView soundIcon;
    @FXML private Slider musicSlider;
    @FXML private Slider soundSlider;
    @FXML private Label NoBomb;
    @FXML private Label NoHeart;
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
    private void pauseGame() {
        if (isRunning) {
            isPausing = true;
        }
    }

    @FXML
    private void resumeGame() {
        if (isRunning) {
            isPausing = false;
        }
    }

    @FXML
    private void exitGame() {
        isRunning = false;
    }

    @FXML
    private void mouseMoved() {
        moveSound.play();
    }


    public void show() {
        musicPlayer = new MusicPlayer("src//main//resources//com//uet//oop//Musics//Battle.mp3", true);
        musicPlayer.setVolume(0.1);
        musicPlayer.play();
        for (double i = 0.1; i <= 1; i += 0.1) {
            musicPlayer.setVolume(i);
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("src//main//resources//com//uet//oop//FXML//InGame.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 720, 540);
            Stage stage = BombermanGame.mainStage;
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        selectSound = new MusicPlayer("src//main//resources//com//uet//oop//Sounds//Select.wav", false);
        moveSound = new MusicPlayer("src//main//resources//com//uet//oop//Sounds//Move.wav", false);
        explosionSound = new MusicPlayer("src//main//resources//com//uet//oop//Sounds//Explosion.mp3", false);
        loading.setVisible(false);
    }
}
