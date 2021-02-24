package com.swtec.concurrent;

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
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class ConcurrentRequestWeekForecast implements IConcurrentRequest {

    private final RetrofitClient retrofitClient = RetrofitClient.INSTANCE;

    private OnResultListener<List<Weather>> mListener;

    private final CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    public void setOnResultListener(OnResultListener listener) {
        mListener = listener;
    }

    @Override
    public void removeOnResultLister() {
        mListener = null;
        mDisposables.dispose();
    }

    @Override
    public void performRequest() {
        Disposable disposable = generateObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailure);
        mDisposables.add(disposable);
    }

    private void onSuccess(List<DailyForecast> forecasts) {
        if (mListener != null) {
            mListener.onSuccess(dailyForecast2Weather(forecasts));
        }
    }

    private void onFailure(Throwable t) {
        if (mListener != null) {
            mListener.onFailure(t);
        }
    }

    private Observable<List<DailyForecast>> generateObservable() {
        return new Observable<List<DailyForecast>>() {
            @Override
            protected void subscribeActual(@NonNull Observer<? super List<DailyForecast>> observer) {
                try {
                    Response<WeatherForecast> response = retrofitClient.getWeatherForecast().execute();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            observer.onNext(response.body().getDaily());
                        }
                    }
                    observer.onComplete();
                } catch (IOException e) {
                    e.printStackTrace();
                    onFailure(e);
                }
            }
        };
    }

    private List<Weather> dailyForecast2Weather(List<DailyForecast> forecasts) {
        List<Weather> weathers = new ArrayList<>();
        for (DailyForecast forecast : forecasts) {
            Weather weather = new Weather(forecast.getDate(), (int) forecast.getTemp().getDay(), null);
            weathers.add(weather);
        }
        return weathers;
    }

}
