package com.uet.oop;

import com.uet.oop.GraphicsControllers.HomeController;
import com.uet.oop.ProcessingUnits.MusicPlayer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class BombermanGame extends Application {
    public static Stage mainStage;
    private static final MusicPlayer musicPlayer =
            new MusicPlayer("src//main//resources//com//uet//oop//Musics//Title.mp3", true);

    @Override
    public void start(Stage primaryStage) {
        musicPlayer.play();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Introduction.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 720, 540);
            primaryStage.setTitle("Bomberman Go");
            primaryStage.getIcons().add(
                    new Image(new File("src/main/resources/com/uet/oop/Images/Background/Icon.png").toURI().toString())
            );
            primaryStage.setScene(scene);
            mainStage = primaryStage;
            primaryStage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        mainStage.setOnCloseRequest(windowEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bomberman Go: Quit");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(
                    new Image(new File("src/main/resources/com/uet/oop/Images/Background/Icon.png").toURI().toString())
            );
            alert.setHeaderText("Quit game?");
            alert.setContentText("Continue");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.isPresent() && action.get() == ButtonType.OK) {
                BombermanGame.mainStage.close();
                musicPlayer.stop();
                mainStage.close();
                System.exit(0);
            }
        });
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
