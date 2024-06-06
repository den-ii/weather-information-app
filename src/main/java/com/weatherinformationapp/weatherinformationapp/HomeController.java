package com.weatherinformationapp.weatherinformationapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * HomeController class
 * Controls user interaction on home-view
 * @author Deni Wisdom Ochiche
 */
public class HomeController implements Initializable {
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
     * Generates data at first instance
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Url currentUrl = new Url();
        currentUrl.geolocation();
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
     * Navigates to home screen
     * @param event
     * @throws IOException
     */
    public void goToHome(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-view.fxml")));
        SceneController.switchScene(root, event);
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
     * Navigates to history screen
     * @param event
     * @throws IOException
     */
    public void goToHistory(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("history-view.fxml")));
        SceneController.switchScene(root, event);
    }
}
