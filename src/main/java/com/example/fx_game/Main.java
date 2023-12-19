package com.example.fx_game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static final int width = 600;
    public static final int height = 800;

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(new Game(), width, height);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}