package com.swtec.repositories;

import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.swtec.data.Weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.RetrofitClient;
import api.model.DailyForecast;
import api.model.WeatherForecast;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

public class WeatherRepository {

    private final MutableLiveData<List<Weather>> mWeekForecast = new MutableLiveData<>();

    public LiveData<List<Weather>> updateWeather(){
        Log.d("WeatherRepository", "updateWeather");
        new RxNetwork().makeRequest();
        return mWeekForecast;
    }

    private class RxNetwork{

        private final List<Weather> weathers = new ArrayList<>();
        public void makeRequest() {
            Log.d("RxWeatherNetwork", "getWeatherWeekForecast");

            //Log.d("RxWeatherNetwork", String.valueOf(weathers.size()));
            Disposable disposable = generateObservable()
                    .flatMapIterable(dailyForecasts -> dailyForecasts)
                    .flatMap(this::dailyForecast2Weather)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(weather -> {
                                Log.d("Subcribe", weather.toString());
                                addWeather(weather);
                            },
                            throwable -> {
                                throwable.printStackTrace();
                                Log.d("Here", "er");
                            },
                            () -> {
                                setWeathers();
                                Log.d("RxWeatherNetwork1", String.valueOf(mWeekForecast.getValue().size()));
                            });

            Log.d("RxWeatherNetwork", "above return statement");
        }

        private void addWeather(Weather updatedWeather) {
            Log.d("RxWeatherNetwork", "addWeather");
            weathers.add(updatedWeather);
        }

        private void setWeathers(){
            mWeekForecast.setValue(weathers);
        }

        private Observable<List<DailyForecast>> generateObservable() {
            return new Observable<List<DailyForecast>>() {
                @Override
                protected void subscribeActual(@NonNull Observer<? super List<DailyForecast>> observer) {
                    try {
                        Log.d("WeatherRepository", "afrer try");
                        Call<WeatherForecast> forecastCall = RetrofitClient.INSTANCE.getWeatherForecast();
                        Response<WeatherForecast> response = forecastCall.execute();
                        if (response.isSuccessful()) {
                            observer.onNext(response.body().getDaily());
                        }
                        else{
                            Log.d("WeatherRepository", "error");
                        }
                        observer.onComplete();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        }

        private Observable<Weather> dailyForecast2Weather(DailyForecast dailyForecastList) {
            return new Observable<Weather>() {
                @Override
                protected void subscribeActual(@NonNull Observer<? super Weather> observer) {
                    Weather weather = new Weather(dailyForecastList.getDate(), (int) dailyForecastList.getTemp().getDay(), null);
                    observer.onNext(weather);
                    observer.onComplete();
                }
            };
        }
    }
}
