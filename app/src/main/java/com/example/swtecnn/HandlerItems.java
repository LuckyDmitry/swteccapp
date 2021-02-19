package com.example.swtecnn;

import java.util.ArrayList;
import java.util.List;

import api.model.CurrentWeather;
import api.model.DailyForecast;

public class HandlerItems {


    private static final ArrayList<DateWeather> dayOfWeeks = new ArrayList<>();
    private static String currentTemperature;
    private static String currentHumidity;

    public static void setData(CurrentWeather currentWeather, List<DailyForecast> forecasts){
        parseWeatherHumidity(currentWeather);
        parseWeather(forecasts);
    }

    public static boolean isDataAvailable(){
        return (!dayOfWeeks.isEmpty()) && (currentTemperature != null && currentHumidity != null);
    }

    public static ArrayList<DateWeather> getDayOfWeeks() {

        return dayOfWeeks;
    }

    public static String getCurrentTemperature() {

        return currentTemperature;
    }

    public static String getCurrentHumidity() {

        return currentHumidity;
    }

    private static void parseWeather(List<DailyForecast> weatherForecast){
        dayOfWeeks.clear();
        if(weatherForecast == null){
            dayOfWeeks.add(new DateWeather(
                    "Download error",
                    "",
                    0));
        }
        else {
            for (int i = 0; i < weatherForecast.size(); ++i) {
                dayOfWeeks.add(HandlerItems.getItemWeather(weatherForecast.get(i)));
            }
        }
    }

    private static void parseWeatherHumidity(CurrentWeather weatherForecast){

        currentTemperature = (int) weatherForecast.getTemp() + " \u2103";
        currentHumidity =  String.valueOf(weatherForecast.getHumidity());
    }

    private static DateWeather getItemWeather(DailyForecast forecast) {
        return new DateWeather(
                forecast.getDate(),
                (int) forecast.getTemp().getDay() + " \u2103",
                R.drawable.cloudy);
    }

    public static ArrayList<RightMenuElement> generateRightMenuElements() {

        ArrayList<RightMenuElement> rightMenuBar = new ArrayList<>();
        rightMenuBar.add(new RightMenuElement("Backyard", false, false, false));
        rightMenuBar.add(new RightMenuElement("Back Patio", false, false, false));
        rightMenuBar.add(new RightMenuElement("Front Yard", false, false, false));
        rightMenuBar.add(new RightMenuElement("Garden", false, true, true));
        rightMenuBar.add(new RightMenuElement("Porch", false, false, false));
        return rightMenuBar;
    }
}
