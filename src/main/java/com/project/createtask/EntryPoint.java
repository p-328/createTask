package com.project.createtask;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EntryPoint extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EntryPoint.class.getResource("hangman-ui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Hangman");
        stage.setScene(scene);
        stage.getIcons().add(
                new Image(Objects.requireNonNull(EntryPoint.class.getResourceAsStream("Icon.png"))
        ));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}