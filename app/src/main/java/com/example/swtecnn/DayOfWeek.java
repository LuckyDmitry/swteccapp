package com.example.swtecnn;

public class DayOfWeek {

    String date;
    String temperature;
    int weatherImage;

    public DayOfWeek(String date, String temperature, int weatherImage) {
        this.date = date;
        this.temperature = temperature;
        this.weatherImage = weatherImage;
    }

    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public int getWeatherImage() {
        return weatherImage;
    }

}
