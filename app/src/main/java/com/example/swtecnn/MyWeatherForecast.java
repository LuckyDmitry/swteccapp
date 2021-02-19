package com.example.swtecnn;

import java.util.ArrayList;
import java.util.List;

import api.model.CurrentWeather;
import api.model.DailyForecast;

public class MyWeatherForecast {


    public static List<DailyForecast> getWeekForecast(){
        List<DailyForecast> ls  = new ArrayList<>();
        return ls;
    }
    public static CurrentWeather getCurrentForecast(){
        return new CurrentWeather(23, 12);
    }


}
