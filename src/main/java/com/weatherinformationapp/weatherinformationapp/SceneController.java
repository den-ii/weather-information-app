package com.weatherinformationapp.weatherinformationapp;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * SceneController Class
 * Handle scene switching
 * @author Deni Wisdom Ochiche
 */
public class SceneController {

    static Stage stage;
    private static Scene scene;
    private static Parent root;

    /**
     * Navigates to screen specified.
     * @param roots
     * @param event
     * @throws IOException
     */
    public static void switchScene(Parent roots, ActionEvent event) throws IOException {
        root = roots;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 700, 500);
        stage.setScene(scene);
        stage.show();
    }
    /**
     *  Navigates to screen specified.
     * @param roots
     * @throws IOException
     */
    public static void switchScene(Parent roots) throws IOException {
        root = roots;
        scene = new Scene(root, 700, 500);
        stage.setScene(scene);
        stage.show();
    }
}
