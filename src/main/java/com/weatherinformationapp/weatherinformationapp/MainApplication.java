package com.weatherinformationapp.weatherinformationapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Main Application
 * Entry point to our GUI Application
 * It's responsible for setting initial stage and scene;
 * @author Deni Wisdom Ochiche
 */
public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.
                getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Weather Information App");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        try {
            launch();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
