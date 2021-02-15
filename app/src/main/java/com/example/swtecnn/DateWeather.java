package com.example.swtecnn;

import android.os.Parcel;
import android.os.Parcelable;

public class DateWeather implements Parcelable {

    private final String date;
    private final String temperature;
    private final int weatherImage;

    public DateWeather(String date, String temperature, int weatherImage) {
        this.date = date;
        this.temperature = temperature;
        this.weatherImage = weatherImage;
    }

    protected DateWeather(Parcel in) {
        date = in.readString();
        temperature = in.readString();
        weatherImage = in.readInt();
    }

    public static final Creator<DateWeather> CREATOR = new Creator<DateWeather>() {
        @Override
        public DateWeather createFromParcel(Parcel in) {
            return new DateWeather(in);
        }

        @Override
        public DateWeather[] newArray(int size) {
            return new DateWeather[size];
        }
    };

    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public int getWeatherImage() {
        return weatherImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.temperature);
        dest.writeInt(weatherImage);
    }
}
