package com.example.swtecnn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import static com.example.swtecnn.MainActivity.EXTRA_MESSAGE;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Trifonov", "onCreate" + this.getClass());
        Bundle bundle = getIntent().getExtras();
        DateWeather dateWeather = (DateWeather)bundle.getParcelable(EXTRA_MESSAGE);
        setContentView(R.layout.activity_weather);

        ImageView weatherImageView = findViewById(R.id.activity_weather__iv_weatherPicture);
        weatherImageView.setImageResource(dateWeather.getWeatherImage());

        TextView temperatureTextView = findViewById(R.id.activity_weather__tv_temperature);
        temperatureTextView.setText(dateWeather.getTemperature());

        TextView dateTextView = findViewById(R.id.activity_weather__tv_centerDate);
        dateTextView.setText(dateWeather.getDate());
    }
}
