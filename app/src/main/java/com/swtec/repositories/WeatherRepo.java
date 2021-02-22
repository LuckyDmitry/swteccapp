package com.swtec.repositories;

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
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class WeatherRepo {

    private final MutableLiveData<List<Weather>> mWeekForecast = new MutableLiveData<>();

    public LiveData<List<Weather>> updateWeather(){
        new RxNetwork().makeRequest();
        return mWeekForecast;
    }

    private class RxNetwork{

        private final List<Weather> weathers = new ArrayList<>();
        private final RetrofitClient retrofitClient = RetrofitClient.INSTANCE;

        public void makeRequest() {

            Disposable disposable = generateObservable()
                    .flatMapIterable(dailyForecasts -> dailyForecasts)
                    .flatMap(this::dailyForecast2Weather)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::addWeather,
                            Throwable::printStackTrace,
                            this::setWeathers);
        }

        private void addWeather(Weather updatedWeather) {
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
                        Response<WeatherForecast> response = retrofitClient.getWeatherForecast().execute();

                        if (response.isSuccessful()){
                            observer.onNext(response.body().getDaily());
                        }
                        observer.onComplete();
                    } catch (IOException | NullPointerException e) {
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
