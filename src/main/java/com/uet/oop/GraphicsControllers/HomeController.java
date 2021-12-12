package com.uet.oop.GraphicsControllers;

import com.uet.oop.BombermanGame;
import com.uet.oop.Entities.Bomberman;
import com.uet.oop.Entities.Game;
import com.uet.oop.ProcessingUnits.MusicPlayer;
import com.uet.oop.ProcessingUnits.RunningGame;
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

    private static final List<Image> bombermanImage = new ArrayList<>(Arrays.asList(
            new Image(new File("src/main/resources/com/uet/oop/Images/Bomberman/Yellow/33.gif").toURI().toString()),
            new Image(new File("src/main/resources/com/uet/oop/Images/Bomberman/Blue/33.gif").toURI().toString()),
            new Image(new File("src/main/resources/com/uet/oop/Images/Bomberman/Green/33.gif").toURI().toString()),
            new Image(new File("src/main/resources/com/uet/oop/Images/Bomberman/Red/33.gif").toURI().toString())
    ));
    private int imageIndex = 0;
    private static final MusicPlayer musicPlayer = new MusicPlayer("src/main/resources/com/uet/oop/Musics/Select.mp3", true);
    private static final MusicPlayer selectSound = new MusicPlayer("src/main/resources/com/uet/oop/Sounds/Select.wav", false);
    private List<ImageView> images;
    private int mapIndex = 0;
    public static double musicVolume = 1;
    public static double soundVolume = 1;

    @FXML
    private void previousMap() {
        selectSound.play();
        mapIndex--;
        System.out.println("previous map");
    }

    @FXML
    private void nextMap() {
        selectSound.play();
        mapIndex++;
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
        imageIndex = imageIndex % 4;
        bomberman.setImage(bombermanImage.get(imageIndex));
    }

    @FXML
    private void playGame() {
        musicVolume = musicPlayer.getVolume();
        soundVolume = selectSound.getVolume();

        selectSound.play();
        musicPlayer.stop();
        selectSound.stop();

        Game game = new Game();
        game.initialize("src/main/resources/com/uet/oop/Maps/map2.txt");
        Bomberman bomberman = game.getBoard().getBomberman();
        switch(imageIndex) {
            case (0) -> bomberman.setColor("Yellow");
            case (1) -> bomberman.setColor("Blue");
            case (2) -> bomberman.setColor("Green");
            case (3) -> bomberman.setColor("Red");
        }
        GameController gc = new GameController();
        RunningGame rg = new RunningGame(gc);
        gc.show(game, bomberman);
        rg.start();
    }

    @FXML
    private void quitGame() {
        selectSound.play();
        musicPlayer.stop();
        selectSound.stop();
        new BombermanGame().start(BombermanGame.mainStage);
    }

    @FXML
    private void muteMusic() {
        selectSound.play();
        if (musicSlider.getValue() > 0) {
            musicIcon.setImage(new Image(new File("src/main/resources/com/uet/oop/Images/Background/muteMusic.png").toURI().toString()));
            musicSlider.setValue(0);
            musicPlayer.setVolume(0);
        } else {
            musicIcon.setImage(new Image(new File("src/main/resources/com/uet/oop/Images/Background/music.png").toURI().toString()));
            musicSlider.setValue(musicVolume);
            musicPlayer.setVolume(musicVolume);
        }
    }

    @FXML
    private void muteSound() {
        selectSound.play();
        if (soundSlider.getValue() > 0) {
            soundIcon.setImage(new Image(new File("src/main/resources/com/uet/oop/Images/Background/muteSound.png").toURI().toString()));
            soundSlider.setValue(0);
            selectSound.setVolume(0);
        } else {
            soundIcon.setImage(new Image(new File("src/main/resources/com/uet/oop/Images/Background/sound.png").toURI().toString()));
            soundSlider.setValue(soundVolume);
            selectSound.setVolume(soundVolume);
        }
    }

    @FXML
    private void musicSliderOnDragged() {
        selectSound.play();
        double value = musicSlider.getValue();
        if (value == 0) {
            musicIcon.setImage(new Image(new File("src/main/resources/com/uet/oop/Images/Background/muteMusic.png").toURI().toString()));
            musicPlayer.setVolume(0);
        } else {
            musicIcon.setImage(new Image(new File("src/main/resources/com/uet/oop/Images/Background/music.png").toURI().toString()));
            musicPlayer.setVolume(value);
            musicVolume = value;
        }
    }

    @FXML
    private void soundSliderOnDragged() {
        selectSound.play();
        double value = soundSlider.getValue();
        if (value == 0) {
            soundIcon.setImage(new Image(new File("src/main/resources/com/uet/oop/Images/Background/muteSound.png").toURI().toString()));
            selectSound.setVolume(0);
        } else {
            soundIcon.setImage(new Image(new File("src/main/resources/com/uet/oop/Images/Background/sound.png").toURI().toString()));
            selectSound.setVolume(value);
            soundVolume = value;
        }
    }

    public void show() {
        musicPlayer.play();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BombermanGame.class.getResource("FXML/Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 720, 540);
            Stage stage = BombermanGame.mainStage;
            stage.setScene(scene);
            stage.show();
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
