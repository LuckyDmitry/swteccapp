package com.swtecnn.java_threads;

import android.os.Handler;

import com.example.swtecnn.HandlerItems;
import com.example.swtecnn.MainActivity;
import com.example.swtecnn.MyWeatherForecast;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import api.model.CurrentWeather;
import api.model.DailyForecast;

public class ThreadWithHandler implements Runnable{

    List<DailyForecast> dailyForecasts;
    CurrentWeather currentWeather;
    WeakReference<MainActivity> weakReference;
    Handler handler;

    public ThreadWithHandler(WeakReference<MainActivity> weakReference){
        this.weakReference = weakReference;
    }

    @Override
    public void run() {
        handler = new Handler(weakReference.get().getMainLooper());
        try {
            this.dailyForecasts = MyWeatherForecast.getWeekForecast();
            this.currentWeather = MyWeatherForecast.getCurrentForecast();
            handler.post(() -> {
                HandlerItems.setData(currentWeather, dailyForecasts);
                weakReference.get().updateWeatherData();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
