package com.swtec.data;

public class CurrentWeather {


    private final int temperature;
    private final int humidity;

    public CurrentWeather(int temperature, int humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getTemperature() {
        return String.valueOf(temperature) + "\u2103";
    }

    public int getHumidity() {
        return humidity;
    }
}
