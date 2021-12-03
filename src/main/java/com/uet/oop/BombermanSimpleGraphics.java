package com.uet.oop;

import com.uet.oop.Entities.Bot;
import com.uet.oop.Entities.Game;
import com.uet.oop.Entities.Piece;
import com.uet.oop.ProcessingUnits.AutomaticBot;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombermanSimpleGraphics extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Game game = new Game();
        game.initialize("src/main/resources/com/uet/oop/maps/map1.txt");
        Pane root = new Pane();
        root.setPrefSize(600, 400);
        Label label = new Label();
        label.setFont(new Font(18));
        label.setText(game.getBoard().toString());
        root.getChildren().add(label);
        List<AutomaticBot> autobots = new ArrayList<>();
        for (Piece piece : game.getBoard().getBots()) {
            autobots.add(new AutomaticBot(game, (Bot) piece));
        }
        Scene scene = new Scene(root, 600, 400);
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if (event.getSource() != scene) return;
//                switch (event.getCode()) {
//                    case UP -> {
//                        System.out.println("UP");
//                    }
//                    case DOWN -> {
//                        System.out.println("DOWN");
//                    }
//                    case LEFT -> {
//                        System.out.println("LEFT");
//                    }
//                    case RIGHT -> {
//                        System.out.println("RIGHT");
//                    }
//                }
//            }
//        });
        stage.setTitle("Hello!");
        stage.setScene(scene);
        for (AutomaticBot bot : autobots) {
            bot.start();
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}