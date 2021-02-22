package com.swtec.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swtec.data.Weather;
import com.swtec.repositories.WeatherRepository;

import java.util.List;

public class WeatherViewModel extends ViewModel {

    private LiveData<List<Weather>> mLiveListWeather;
    private WeatherRepository mWeatherRepository;

    public WeatherViewModel(){
        Log.d("WeatherViewModel", "WeatherViewModel");
        mWeatherRepository = new WeatherRepository();
    }

    public LiveData<List<Weather>> updateWeather(){
        Log.d("WeatherViewModel", "getWeather");
        if(mLiveListWeather == null){
            mLiveListWeather = mWeatherRepository.updateWeather();
        }
        return mLiveListWeather;
    }

}
