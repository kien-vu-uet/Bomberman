package com.uet.oop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloController {
    @FXML
    private Rectangle rectangle;
    @FXML
    private Pane root;

    private boolean setUp = false;

    @FXML
    private void keyPressed() {
        if (setUp) return;
        double x = rectangle.getLayoutX();
        double y = rectangle.getLayoutY();
        root.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            switch (event.getCode()) {
                case UP -> System.out.println("UP");
                case DOWN -> System.out.println("DOWN");
                case LEFT -> System.out.println("LEFT");
                case RIGHT -> System.out.println("RIGHT");
            }
        });
        setUp = true;
    }

    public static void loadStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}