package com.uet.oop.GraphicsControllers;

import com.uet.oop.BombermanGame;
import com.uet.oop.ProcessingUnits.MusicPlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class HomeController {
    @FXML private ImageView map;
    @FXML private ImageView bomberman;
    @FXML private Slider musicSlider;
    @FXML private Slider soundSlider;
    @FXML private ImageView musicIcon;
    @FXML private ImageView soundIcon;
    @FXML private ImageView loading;

    private List<Image> bombermanImage;
    private int imageIndex = 0;
    private MusicPlayer musicPlayer;
    private MusicPlayer selectSound;
    private MusicPlayer moveSound;
    private List<ImageView> images;

    @FXML
    private void previousMap() {
        selectSound.play();
        System.out.println("previous map");
    }

    @FXML
    private void nextMap() {
        selectSound.play();
        System.out.println("next map");
    }

    @FXML
    private void previousBomberman() {
        selectSound.play();
        imageIndex--;
        if (imageIndex < 0) imageIndex += bombermanImage.size();
        bomberman.setImage(bombermanImage.get(imageIndex));
    }

    @FXML
    private void nextBomberman() {
        selectSound.play();
        imageIndex++;
        bomberman.setImage(bombermanImage.get(imageIndex));
    }

    @FXML
    private void playGame() {
        selectSound.play();
        for (double i = musicPlayer.getVolume(); i > 0; i -= 0.1) {
            musicPlayer.setVolume(i);
        }
        musicPlayer.stop();
        new GameController().show();
    }

    @FXML
    private void quitGame() {
        selectSound.play();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bomberman Go");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(new File("src//main//resources//com//uet//oop//Images//Background//Icon.png").toURI().toString()));
        alert.setHeaderText("Quit game?");
        alert.setContentText("Continue");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.isPresent() && action.get() == ButtonType.OK) {
            BombermanGame.mainStage.close();
            musicPlayer.stop();
            selectSound.stop();
            moveSound.stop();
            System.exit(0);
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
    private void muteSound() {
        selectSound.play();
        if (soundSlider.getValue() > 0) {
            soundIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//muteSound.png").toURI().toString()));
            soundSlider.setValue(0);
            selectSound.setVolume(0);
            moveSound.setVolume(0);
        } else {
            soundIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//sound.png").toURI().toString()));
            soundSlider.setValue(1);
            selectSound.setVolume(1);
            moveSound.setVolume(1);
        }
    }

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
    private void soundSliderOnDragged() {
        selectSound.play();
        double value = soundSlider.getValue();
        if (value == 0) {
            soundIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//muteSound.png").toURI().toString()));
            selectSound.setVolume(0);
            moveSound.setVolume(0);
        } else {
            soundIcon.setImage(new Image(new File("src//main//resources//com//uet//oop//Images//Background//sound.png").toURI().toString()));
            selectSound.setVolume(value);
            moveSound.setVolume(value);
        }
    }

    @FXML
    private void onMouseMoved() {
        moveSound.play();
    }

    public void show() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("src//main//resources//com//uet//oop//FXML//Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 720, 540);
            Stage stage = BombermanGame.mainStage;
            stage.setTitle("Bomberman Go");
            stage.getIcons().add(new Image(new File("src//main//resources//com//uet//oop//Images//Background//Icon.png").toURI().toString()));
            stage.setScene(scene);
            stage.show();
            bombermanImage = new ArrayList<>();
            bombermanImage.add(new Image(new File("src//main//resources//com//uet//oop//Images//Bomberman//Yellow//33.gif").toURI().toString()));
            bombermanImage.add(new Image(new File("src//main//resources//com//uet//oop//Images//Bomberman//Blue//33.gif").toURI().toString()));
            bombermanImage.add(new Image(new File("src//main//resources//com//uet//oop//Images//Bomberman//Green//33.gif").toURI().toString()));
            bombermanImage.add(new Image(new File("src//main//resources//com//uet//oop//Images//Bomberman//Red//33.gif").toURI().toString()));
            musicPlayer = new MusicPlayer("src//main//resources//com//uet//oop//Musics//Select.mp3", true);
            musicPlayer.setVolume(0.1);
            musicPlayer.play();
            for (double i = 0.1; i <= 1; i += 0.1) {
                musicPlayer.setVolume(i);
            }
            selectSound = new MusicPlayer("src//main//resources//com//uet//oop//Sounds//Select.wav", false);
            moveSound = new MusicPlayer("src//main//resources//com//uet//oop//Sounds//Move.wav", false);
            loading.setVisible(false);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void setImages(List<ImageView> list) {
        images = list;
    }

    public List<ImageView> getImages() {
        return images;
    }
}
