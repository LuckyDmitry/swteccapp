package com.swtecnn.java_threads;

import com.example.swtecnn.MyWeatherForecast;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import api.model.CurrentWeather;
import api.model.DailyForecast;

public class ThreadPoolExecutor implements Runnable {


    Executor executor;

    ThreadCallback threadCallback;
    CurrentWeather currentWeather;
    List<DailyForecast> dailyForecasts;

    public ThreadPoolExecutor(ThreadCallback threadCallback){
        this.threadCallback = threadCallback;
        executor = Executors.newFixedThreadPool(1);
    }

    @Override
    public void run() {


        executor.execute(() -> {
            try {
                currentWeather = MyWeatherForecast.getCurrentForecast();
                dailyForecasts = MyWeatherForecast.getWeekForecast();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> threadCallback.setData(currentWeather, dailyForecasts));
    }
}
