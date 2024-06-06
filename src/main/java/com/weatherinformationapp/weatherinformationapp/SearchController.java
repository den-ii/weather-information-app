package com.weatherinformationapp.weatherinformationapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

/**
 * SearchController class
 * Controller for search-view.fxml
 * @author Deni Wisdom Ochiche
 */

public class SearchController {
    @FXML
    private TextField input;
    @FXML
    private Button check;
    @FXML
    private Label error;
    @FXML
    private Button home;
    @FXML
    private Button search;
    @FXML
    private Button history;

    /**
     * Navigates to search screen
     * @param event
     * @throws IOException
     */
    public void goToSearch(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("search-view.fxml"));
        SceneController.switchScene(root, event);
    }

    /**
     * Navigates to home screen
     * @param event
     * @throws IOException
     */
    public void goToHome(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-view.fxml")));
        SceneController.switchScene(root, event);
    }

    /**
     * Navigates to history screen
     * @param event
     * @throws IOException
     */
    public void goToHistory(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("history-view.fxml")));
        SceneController.switchScene(root, event);
    }

    /**
     * Navigates to view screen to view weather
     * details of the location specified.
     * @param event
     * @throws IOException
     */
    public void searchChange(ActionEvent event) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("view-view.fxml"));
        Parent root = loader.load();
        ViewController viewController = loader.getController();
        Url newUrl = searchLocation();
        if (newUrl != null) {
            viewController.activate(newUrl);
            Url.addToHistory(newUrl);
        }
        SceneController.switchScene(root, event);
    }

    /**
     * Retrieves value from user and returns
     * url instance.
     * @return URL url
     */
    public Url searchLocation(){
        String value = input.getText();
        Url newUrl = new Url();
        try {
            newUrl.setupUrl(value);
            return newUrl;

        } catch (Exception e){
            error.setText("Something went wrong");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
