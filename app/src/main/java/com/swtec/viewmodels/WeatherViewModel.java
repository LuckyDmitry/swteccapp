package com.swtec.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.swtec.data.Weather;
import com.swtec.repositories.WeatherRepo;

import java.util.List;

public class WeatherViewModel extends ViewModel {

    private LiveData<List<Weather>> mLiveListWeather;
    private final WeatherRepo mWeatherRepo;

    public WeatherViewModel(){
        mWeatherRepo = new WeatherRepo();
    }

    public LiveData<List<Weather>> updateWeather(){
        if(mLiveListWeather == null){
            mLiveListWeather = mWeatherRepo.updateWeather();
        }
        return mLiveListWeather;
    }

}
