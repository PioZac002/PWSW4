package com.example.wydarzenie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class WydarzenieApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WydarzenieApp.class.getResource("logowanie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 380, 250);
        stage.setTitle("Aplikacja Wydarzenia");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}