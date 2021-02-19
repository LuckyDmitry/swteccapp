package com.example.swtecnn;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import api.RetrofitClient;
import api.RetrofitService;
import api.model.CurrentWeather;
import api.model.CurrentWeatherForecast;
import api.model.DailyForecast;
import api.model.WeatherForecast;
import retrofit2.Retrofit;

public class MyWeatherForecast {

    private static CurrentWeather currentWeatherForecast;
    private static List<DailyForecast> weekWeatherForecast; // 7 days
    private static final RetrofitClient retrofit = RetrofitClient.INSTANCE;

    public static List<DailyForecast> getWeekForecast() throws IOException {
        if(weekWeatherForecast == null) {
            try {
                weekWeatherForecast = Objects.requireNonNull(retrofit.getWeatherForecast().execute().body()).getDaily();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return weekWeatherForecast;
    }

    public static CurrentWeather getCurrentForecast() throws IOException {
        if(currentWeatherForecast == null) {
            try {
                currentWeatherForecast = Objects.requireNonNull(retrofit.getCurrentWeather().execute().body()).getWeather();
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return currentWeatherForecast;
    }

}
