package com.swtecnn.java_threads;

import java.util.List;

import api.model.CurrentWeather;
import api.model.DailyForecast;

public interface ThreadCallback {
    void setData(CurrentWeather currentWeather, List<DailyForecast> weekForecast);
}
