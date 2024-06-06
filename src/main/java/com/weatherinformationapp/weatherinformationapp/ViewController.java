package com.weatherinformationapp.weatherinformationapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.Objects;

/**
 * ViewController class
 * Controller for view-view.fxml
 * @author Deni Wisdom Ochiche
 */
public class ViewController  {
    @FXML
    private ImageView weather;
    @FXML
    private Label degree;
    @FXML
    private Label countryCode;
    @FXML
    private Label city;
    @FXML
    private Label humidity;
    @FXML
    private Label wind_speed;

    @FXML
    private Button home;
    @FXML
    private Button search;
    @FXML
    private Button history;
    boolean isCelsius = true;
    String degreeSymbol = "°C";
    int temperature = 0;


    /**
     * populates view with data
     * @param currentUrl Url instance.
     */
    public void activate(Url currentUrl) {
        Image image = new Image(currentUrl.getImgUrl());
        weather.setImage(image);
        temperature = Integer.parseInt(currentUrl.getTemperature());
        degree.setText(temperature+ degreeSymbol);
        city.setText(currentUrl.getCity());
        countryCode.setText(currentUrl.getCountry());
        wind_speed.setText("Wind Speed: " + currentUrl.getWind_speed());
        humidity.setText("Humidity: " + currentUrl.getHumidity());

    }

    /**
     * Switches from celsius to fahrenheit and vice-versa
     */
    public void convertDegree(){
        if (isCelsius){
            int res = (temperature * 9/5) + 32;
            isCelsius = false;
            temperature = res;
            degreeSymbol = "°F";
            degree.setText(temperature+ degreeSymbol);

        } else {
            int res = (temperature - 32) * 5/9;
            isCelsius = true;
            temperature = res;
            degreeSymbol = "°C";
            degree.setText(temperature+ degreeSymbol);
        }
    }

    /**
     * Navigates to search screen
     * @param event
     * @throws IOException
     */
    public void goToSearch(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("search-view.fxml")));
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
}
