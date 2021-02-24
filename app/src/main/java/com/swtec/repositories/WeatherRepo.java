package com.swtec.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.swtec.concurrent.ConcurrentRequestCurrentWeather;
import com.swtec.concurrent.ConcurrentRequestWeekForecast;
import com.swtec.concurrent.IConcurrentRequest;
import com.swtec.concurrent.OnResultListener;
import com.swtec.data.CurrentWeather;
import com.swtec.data.Weather;

import java.util.List;

public class WeatherRepo {

    private final IConcurrentRequest mRequestWeekForecast;
    private final IConcurrentRequest mRequestCurrentWeather;

    private final MutableLiveData<List<Weather>> mWeekForecast = new MutableLiveData<>();
    private final MutableLiveData<CurrentWeather> mCurrentWeather = new MutableLiveData<>();

    public WeatherRepo() {
        mRequestWeekForecast = new ConcurrentRequestWeekForecast();
        mRequestWeekForecast.setOnResultListener(new OnResultListener<List<Weather>>() {
            @Override
            public void onSuccess(List<Weather> weathers) {
                mWeekForecast.setValue(weathers);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("WeatherRepo", "onFailureWeek", t);
            }
        });

        mRequestCurrentWeather = new ConcurrentRequestCurrentWeather();
        mRequestCurrentWeather.setOnResultListener(new OnResultListener<CurrentWeather>() {
            @Override
            public void onSuccess(CurrentWeather element) {
                mCurrentWeather.setValue(element);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("WeatherRepo", "onFailurecurrent", t);
            }
        });

    }

    public void fetchForecast() {
        mRequestWeekForecast.performRequest();
    }

    public void fetchCurrent() { mRequestCurrentWeather.performRequest(); }


    public LiveData<List<Weather>> getWeather() {
        return mWeekForecast;
    }

    public LiveData<CurrentWeather> getCurrent() {
        return mCurrentWeather;
    }

    public void onCleared() {
        mRequestWeekForecast.removeOnResultLister();
        mRequestCurrentWeather.removeOnResultLister();
    }
}
