package com.swtec.concurrent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import api.RetrofitClient;
import api.model.CurrentWeather;
import api.model.CurrentWeatherForecast;
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

public class ConcurrentRequestCurrentWeather implements IConcurrentRequest {


    private final RetrofitClient mRetrofitClient = RetrofitClient.INSTANCE;
    private OnResultListener<com.swtec.data.CurrentWeather> mListener;

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
        Disposable disposable = fetchObservableCurrent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailure);
        mDisposables.add(disposable);

    }

    private void onSuccess(CurrentWeather currentWeather) {
        if (mListener != null) {
            mListener.onSuccess(converter(currentWeather));
        }
    }

    private void onFailure(Throwable t) {
        if (mListener != null) {
            mListener.onFailure(t);
        }
    }


    private Observable<api.model.CurrentWeather> fetchObservableCurrent(){
        return new Observable<CurrentWeather>() {
            @Override
            protected void subscribeActual(@NonNull Observer<? super api.model.CurrentWeather> observer) {
                try {
                    Response<CurrentWeatherForecast> response = mRetrofitClient.getCurrentWeather().execute();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            observer.onNext(response.body().getWeather());
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

    private com.swtec.data.CurrentWeather converter(CurrentWeather weather){
        return new com.swtec.data.CurrentWeather((int) weather.getTemp(), weather.getHumidity());
    }
}
