package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pane.RootPane;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        new Game();
        launch();
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(RootPane.getRootPane());
        stage.setScene(scene);
        stage.setTitle("Let's Read");
        stage.setResizable(false);
        stage.show();
    }
}