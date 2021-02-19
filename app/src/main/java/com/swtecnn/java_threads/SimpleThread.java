package com.swtecnn.java_threads;

import com.example.swtecnn.MyWeatherForecast;

import java.io.IOException;
import java.util.List;

import api.model.CurrentWeather;
import api.model.DailyForecast;

public class SimpleThread implements Runnable {

    private List<DailyForecast> forecast;
    private CurrentWeather currentWeather;
    private final ThreadCallback callbackFunction;

    public SimpleThread(ThreadCallback function) {

        callbackFunction = function;
    }

    @Override
    public void run() {

        try {
            forecast = MyWeatherForecast.getWeekForecast();
            currentWeather = MyWeatherForecast.getCurrentForecast();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.callbackFunction.setData(currentWeather, forecast);
    }
}
