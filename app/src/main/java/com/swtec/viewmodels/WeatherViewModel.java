package com.swtec.viewmodels;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.swtec.data.Weather;
import com.swtec.repositories.WeatherRepo;

import java.util.List;

public class WeatherViewModel extends ViewModel {

    private final WeatherRepo mWeatherRepo;

    public WeatherViewModel() {
        Log.d("WeatherViewModel", "constructor");

        mWeatherRepo = new WeatherRepo();
        mWeatherRepo.fetchForecast();
    }

    public LiveData<List<Weather>> getWeather() {
        return mWeatherRepo.getWeather();
    }

    @Override
    protected void onCleared() {
        Log.d("WeatherViewModel", "onCleared");
        mWeatherRepo.onCleared();
        super.onCleared();
    }
}
