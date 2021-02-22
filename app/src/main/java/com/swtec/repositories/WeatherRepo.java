package com.swtec.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.swtec.concurrent.ConcurrentRequestImpl;
import com.swtec.concurrent.IConcurrentRequest;
import com.swtec.concurrent.OnResultListener;
import com.swtec.data.Weather;

import java.util.List;

public class WeatherRepo {

    private final IConcurrentRequest mRequest;

    private final MutableLiveData<List<Weather>> mWeekForecast = new MutableLiveData<>();

    public WeatherRepo() {
        mRequest = new ConcurrentRequestImpl();
        mRequest.setOnResultListener(new OnResultListener() {
            @Override
            public void onSuccess(List<Weather> weathers) {
                mWeekForecast.setValue(weathers);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("WeatherRepo", "onFailure", t);
            }
        });
    }

    public void fetchForecast() {
        mRequest.performRequest();
    }

    public LiveData<List<Weather>> getWeather() {
        return mWeekForecast;
    }

    public void onCleared() {
        mRequest.removeOnResultLister();
    }
}
