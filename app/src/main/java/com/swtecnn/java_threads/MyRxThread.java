package com.swtecnn.java_threads;

import android.util.Pair;

import com.example.swtecnn.MyWeatherForecast;
import com.example.swtecnn.HandlerItems;

import java.util.List;

import api.model.CurrentWeather;
import api.model.DailyForecast;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MyRxThread {


    public void run() {
        weatherSource()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((pairWeather) -> HandlerItems.setData(pairWeather.first, pairWeather.second)).dispose();
    }

    Observable<Pair<CurrentWeather, List<DailyForecast>>> weatherSource() {
        return Observable.create(sub -> {
            sub.onNext(new Pair<>(MyWeatherForecast.getCurrentForecast(),
                    MyWeatherForecast.getWeekForecast()));
        });
    }
}
