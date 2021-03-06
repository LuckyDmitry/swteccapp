package com.swtec.data;

import androidx.annotation.NonNull;

public class Weather {


    private final String mDate;

    private final Integer mTemperature;

    private final Integer mWeatherImage;

    public Weather(@NonNull String mDate, @NonNull Integer mTemperature, Integer mWeatherImage) {
        this.mDate = mDate;
        this.mTemperature = mTemperature;
        this.mWeatherImage = mWeatherImage;
    }

    public String getDate() {
        return mDate;
    }

    public String getTemperature() {
        return mTemperature + "\u2103";
    }

    public Integer getWeatherImage() {
        return mWeatherImage;
    }
}
