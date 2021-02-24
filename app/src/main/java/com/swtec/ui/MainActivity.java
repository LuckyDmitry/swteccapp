package com.swtec.ui;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swtecnn.R;
import com.swtec.adapters.WateringPlacesAdapter;
import com.swtec.adapters.WeatherAdapter;
import com.swtec.data.CurrentWeather;
import com.swtec.data.WateringPlace;
import com.swtec.data.Weather;
import com.swtec.viewmodels.WateringPlacesViewModel;
import com.swtec.viewmodels.WeatherViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        TextView temp = findViewById(R.id.main_activity__tv_temperatureCenterLeft);
        TextView humidity = findViewById(R.id.main_activity__tv_humidityCenterRight);

        WeatherViewModel mWeatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        WateringPlacesViewModel mWateringViewModel = ViewModelProviders.of(this).get(WateringPlacesViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.main_activity__rv_dateWeather);
        WeatherAdapter weatherAdapter = new WeatherAdapter(this);
        recyclerView.setAdapter(weatherAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView mWateringRecyclerView = findViewById(R.id.main_activity__rv_placesBoxes);
        WateringPlacesAdapter wateringPlacesAdapter = new WateringPlacesAdapter(this);
        mWateringRecyclerView.setAdapter(wateringPlacesAdapter);
        mWateringRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageButton sprinklerButton = findViewById(R.id.main_activity__ib_sprinkler);
        sprinklerButton.setTag("sprinkler on");
        sprinklerButton.setOnClickListener(v -> {
            String tag = String.valueOf(sprinklerButton.getTag());
            String toastMessage;

            if (tag.equals("sprinkler on")) {
                sprinklerButton.setImageResource(R.drawable.sprinkler_off);
                sprinklerButton.setTag("sprinkler off");
                toastMessage = "All tomorrows activities disabled";
                sprinklerButton.setContentDescription("Button disabled.To turn on double click");
                wateringPlacesAdapter.turnOffCheckBoxes();


            } else {
                sprinklerButton.setImageResource(R.drawable.sprinkler_on);
                sprinklerButton.setTag("sprinkler on");
                toastMessage = "Button enabled";
                sprinklerButton.setContentDescription("Disable tomorrows watering double click");
            }

            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        });


        mWeatherViewModel.getWeather().observe(this, new Observer<List<Weather>>(){

            @Override
            public void onChanged(List<Weather> weathers) {
                weatherAdapter.setWeather(weathers);
            }
        });

        mWeatherViewModel.getCurrent().observe(this, new Observer<CurrentWeather>() {
            @Override
            public void onChanged(CurrentWeather currentWeather) {
                temp.setText(String.valueOf(currentWeather.getTemperature()));
                humidity.setText(String.valueOf(currentWeather.getHumidity()));
            }
        });

        mWateringViewModel.getWateringPlaces().observe(this, new Observer<List<WateringPlace>>() {
            @Override
            public void onChanged(List<WateringPlace> wateringPlaces) {
                wateringPlacesAdapter.setWateringPlaces(wateringPlaces);
            }
        });


    }
}


