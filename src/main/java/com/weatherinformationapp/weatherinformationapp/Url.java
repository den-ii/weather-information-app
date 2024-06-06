package com.weatherinformationapp.weatherinformationapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Url class
 * Contains weather information on location requested.
 * @author Deni Wisdom Ochiche
 */
public class Url {
    /*
     * stores all past searched location
     */
    private static ArrayList<Url> history = new ArrayList<>();
    private static URL url;

    private static final String initialUrl = "http://api.weatherstack.com/current" +
            "?access_key=1d1a62ef9581ed6ee0b98d0ef71cee3a" +
            "&query=%s";
    private static final String APIKEY = "b04f2dec3d4b31d0d26c9b8b3208efa5";

    private String country;
    private String city;
    private Object location;
    private Object current;
    private String temperature;
    private String imgUrl;
    private String humidity;
    private String wind_speed;
    private String formattedDateTime;


    /**
     * Makes request to WeatherStack and populates
     * a URL instance
     * @param city city name.
     */
    public void setupUrl(String city) {
        String urlFormat = initialUrl.formatted(city);
        System.out.println(urlFormat);
        try {
            url = new URL(urlFormat);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder inline = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();
                System.out.println(inline);
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(String.valueOf(inline));
                System.out.println("current: " + data_obj.get("current"));
                current = data_obj.get("current");
                location = data_obj.get("location");
                setVariables();

            }
            //Using the JSON simple library parse the string into a json object
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves weather information of user's location
     * based on  user's ip address.
     */
    public void geolocation() {
        try {
            // Send GET request to ipinfo.io API
            URL geoUrl = new URL("https://ipinfo.io/json");
            // Open a connection to the URL
            HttpURLConnection conn = (HttpURLConnection) geoUrl.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);
            if (responseCode != 200) {
                System.out.println("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder inline = new StringBuilder();
                Scanner scanner = new Scanner(geoUrl.openStream());
                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }
                scanner.close();
                System.out.println(inline);
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(String.valueOf(inline));
                setupUrl((String) data_obj.get("city"));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Populates all instance variables.
     */
    private void  setVariables(){
        imgUrl = "cloudy.png";
        JSONObject currentObj = (JSONObject) getCurrent();
        JSONObject locationObj = (JSONObject) getLocation();
        Long weather_code = (Long) currentObj.get("weather_code");
        if (weather_code == 113) imgUrl = "sunny.png";
        else if (weather_code > 133 && weather_code <= 143) imgUrl = "cloudy.png";
        else if (weather_code >= 176 && weather_code <= 179) imgUrl = "rainy.png";
        else if (weather_code > 179 && weather_code <= 263) imgUrl = "snowy.png";
        else if (weather_code > 263 && weather_code <= 308) imgUrl = "rainy.png";
        else if (weather_code >= 311 && weather_code <= 350) imgUrl = "snowy.png";
        else if (weather_code >= 353 && weather_code <= 359) imgUrl = "rainy.png";
        else if (weather_code > 362) imgUrl = "snowy.png";

        temperature = Long.toString((Long) currentObj.get("temperature"));
        country = (String) locationObj.get("country");
        city = (String) locationObj.get("region");
        humidity = Long.toString((Long) currentObj.get("humidity"));
        wind_speed = Long.toString((Long) currentObj.get("wind_speed"));
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        formattedDateTime = currentDateTime.format(formatter);
    }

    /**
     * Retrieves location details.
     * @return location
     */
    public Object getLocation() {
        return location;
    }

    /**
     * Retrieves weather current details.
     * @return current
     */
    public Object getCurrent() {
        return current;
    }

    /**
     * Retrieves history
     * @return history
     */
    public static List<Url> getHistory(){
       return Collections.unmodifiableList(history);
    }

    /**
     * Retrieves humidity amount
     * @return humidity
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * Retrieves wind speed amount
     * @return humidity
     */
    public String getWind_speed() {
        return wind_speed;
    }

    /**
     * Retrieves humidity amount
     * @return humidity
     */
    public String getDateTime() {
        return formattedDateTime;
    }

    /**
     * Retrieves weather image url
     * @return imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Retrieves city
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Retrieves country
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Retrieves temperature
     * @return temperature
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Adds a Url instance to history
     * @param url Url instance
     */
    public static void addToHistory(Url url){
        history.add(url);
    }
}