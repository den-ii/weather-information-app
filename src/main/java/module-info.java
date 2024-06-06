module com.weatherinformationapp.weatherinformationapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;


    opens com.weatherinformationapp.weatherinformationapp to javafx.fxml;
    exports com.weatherinformationapp.weatherinformationapp;
}