package com.swtecnn.java_threads;

import android.os.AsyncTask;
import android.util.Pair;

import com.example.swtecnn.MyWeatherForecast;
import com.example.swtecnn.MainActivity;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import api.model.CurrentWeather;
import api.model.DailyForecast;

public class MyAsyncTask extends AsyncTask<String, Void, Pair<CurrentWeather, List<DailyForecast>>> implements MyThread{


    private final WeakReference<MainActivity> mainActivity;
    private List<DailyForecast> list = null;
    private CurrentWeather currentWeather = null;
    private final ThreadCallback callbackFunction;

    public MyAsyncTask(WeakReference<MainActivity> mainActivityWeakReference, ThreadCallback function){
        mainActivity = mainActivityWeakReference;
        callbackFunction = function;
    }

    @Override
    protected Pair<CurrentWeather, List<DailyForecast>> doInBackground(String... strings) {
        if(!isCancelled()) {
            try {
                list = MyWeatherForecast.getWeekForecast();
                currentWeather = MyWeatherForecast.getCurrentForecast();
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
            return new Pair<>(currentWeather, list);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Pair<CurrentWeather, List<DailyForecast>> currentWeatherListPair) {
        this.callbackFunction.setData(currentWeather, list);
    }


    @Override
    public void runMyThread() {
        this.execute();
    }
}