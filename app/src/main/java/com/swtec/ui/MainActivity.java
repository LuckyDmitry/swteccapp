package com.swtec.ui;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swtecnn.R;
import com.swtec.adapters.WeatherAdapter;
import com.swtec.data.Weather;
import com.swtec.viewmodels.WeatherViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private WeatherViewModel mWeatherViewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mWeatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        Log.d("MainActivity", "onCreate after setContentView");
        RecyclerView recyclerView = findViewById(R.id.main_activity__rv_dateWeather);
        WeatherAdapter weatherAdapter = new WeatherAdapter(this);
        recyclerView.setAdapter(weatherAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.d("MainActivity", "onCreate after recyclerView");
        mWeatherViewModel.updateWeather().observe(this, new Observer<List<Weather>>(){

            @Override
            public void onChanged(List<Weather> weathers) {
                weatherAdapter.setWeather(weathers);
            }
        });
    }
}


