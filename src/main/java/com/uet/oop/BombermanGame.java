package com.uet.oop;

import com.uet.oop.GraphicsControllers.HomeController;
import com.uet.oop.ProcessingUnits.MusicPlayer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class BombermanGame extends Application {
    public static Stage mainStage;
    private static final MusicPlayer musicPlayer =
            new MusicPlayer("src//main//resources//com//uet//oop//Musics//Title.mp3", true);

    @Override
    public void start(Stage stage) {
        musicPlayer.play();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Introduction.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 720, 540);
            stage.setTitle("Bomberman Go");
            stage.getIcons().add(new Image(new File("src/main/resources/com/uet/oop/Images/Background/Icon.png").toURI().toString()));
            stage.setScene(scene);
            mainStage = stage;
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void playButtonOnClicked() {
        musicPlayer.stop();
        new HomeController().show();
    }

    public static void main(String[] args) {
        launch();
    }
}
