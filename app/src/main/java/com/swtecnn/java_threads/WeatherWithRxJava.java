package com.swtecnn.java_threads;

import android.util.Log;

import com.example.swtecnn.HandlerItems;
import com.example.swtecnn.MainActivity;
import com.example.swtecnn.MyWeatherForecast;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import api.RetrofitClient;
import api.RetrofitClientKt;
import api.model.CurrentWeather;
import api.model.DailyForecast;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeatherWithRxJava {


    WeakReference<MainActivity> weakReference;

    public WeatherWithRxJava(WeakReference<MainActivity> weakReference) {
        Log.d("WeatherWithRxJava", "Constructor");
        this.weakReference = weakReference;
    }

    public void makeRequestCurrentWeather(){
        Log.d("WeatherWithRxJava", "makeRequest");
        Disposable disposable = getCurrentWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currentWeather -> {
                    HandlerItems.setData(currentWeather, null);
                    weakReference.get().updateWeatherData();
                        },
                        Throwable::printStackTrace);
    }

    public Observable<CurrentWeather> getCurrentWeather(){
        return Observable.create(emitter -> {
            CurrentWeather currentWeather = MyWeatherForecast.getCurrentForecast();
            emitter.onNext(currentWeather);
            emitter.onComplete();
        });
    }

    public Observable<List<DailyForecast>> getWeekForecast(){
        return Observable.create(emitter -> {
            List<DailyForecast> weekForecast = MyWeatherForecast.getWeekForecast();
            emitter.onNext(weekForecast);
            emitter.onComplete();
        });
    }

}