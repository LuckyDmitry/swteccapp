package com.example.swtecnn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import api.RetrofitClient;
import api.model.CurrentWeather;
import api.model.DailyForecast;

public class MyWeatherForecast {


    private static CurrentWeather currentWeatherForecast;
    private static List<DailyForecast> weekWeatherForecast; // 7 days
    private static final RetrofitClient retrofit = RetrofitClient.INSTANCE;

    public static List<DailyForecast> getWeekForecast() throws IOException {
        if(weekWeatherForecast == null) {
            weekWeatherForecast = Objects.requireNonNull(retrofit.getWeatherForecast().execute().body()).getDaily();
        }

        return weekWeatherForecast;
    }

    public static CurrentWeather getCurrentForecast() throws IOException {
        if(currentWeatherForecast == null) {
            currentWeatherForecast = Objects.requireNonNull(retrofit.getCurrentWeather().execute().body()).getWeather();
        }

        return currentWeatherForecast;
    }

}
