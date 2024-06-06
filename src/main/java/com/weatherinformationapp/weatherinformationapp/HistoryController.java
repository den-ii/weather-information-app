package com.weatherinformationapp.weatherinformationapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * HistoryController class
 * Controls user interaction on history-view
 * @author Deni Wisdom Ochiche
 */
public class HistoryController implements Initializable {
    @FXML
    private ListView<String> list;
    @FXML
    private Button home;
    @FXML
    private Button search;
    @FXML
    private Button history;

    /**
     * Generates data at first instance
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> searched = new ArrayList<>() ;

        int index = 0;
        List<Url> history = Url.getHistory().reversed();
        for (var u: history){
            System.out.println(searched);
            searched.add(String.format("ID: %d - Country: %s - City: %s - Date: %s", index, u.getCountry(), u.getCity(), u.getDateTime()));
            index++;
        }
        if (!searched.isEmpty()){
            list.getItems().addAll(searched);
        }
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println("clicked");
                System.out.println(list.getSelectionModel().getSelectedItem().split("ID: ")[0]);
                int id = Integer.parseInt(list.getSelectionModel().getSelectedItem().split("ID: ")[1].split(" -")[0]);
                FXMLLoader loader =  new FXMLLoader(getClass().getResource("view-view.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ViewController viewController = loader.getController();
                Url url = history.get(id);
                    if (url != null) {
                        viewController.activate(url);
                    }
                try {
                    SceneController.switchScene(root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });

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
