package com.example.swtecnn;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swtecnn.java_threads.MyAsyncTask;
import com.swtecnn.java_threads.MyThread;
import com.swtecnn.java_threads.ThreadCallback;
import com.swtecnn.java_threads.ThreadsFactory;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

import api.model.CurrentWeather;
import api.model.DailyForecast;

public class MainActivity extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    MyAsyncTask myAsyncTask;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        RecyclerView dateWeatherRecyclingView = findViewById(R.id.main_activity__rv_dateWeather);

        MyThread myThread = new MyAsyncTask(new WeakReference<>(MainActivity.this), new ThreadCallback() {
            @Override
            public void setData(CurrentWeather currentWeather, List<DailyForecast> weekForecast) {
                HandlerItems.setData(currentWeather, weekForecast);
                MainActivity.this.updateWeatherData();
            }
        });
        ThreadsFactory threadsFactory = new ThreadsFactory(myThread);
        threadsFactory.startThread();

        AdapterDateWeather adapterDateWeather = new AdapterDateWeather(HandlerItems.getDayOfWeeks(), content -> {

            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putExtra(EXTRA_MESSAGE, content);
            startActivity(intent);
        });

        dateWeatherRecyclingView.setAdapter(adapterDateWeather);
        LinearLayoutManager dateWeatherLayoutManager = new LinearLayoutManager(this);
        dateWeatherRecyclingView.setLayoutManager(dateWeatherLayoutManager);
        RecyclerView placesBoxesRecyclingView = findViewById(R.id.main_activity__rv_placesBoxes);

        AdapterRightMenu adapterRightMenu = new AdapterRightMenu(HandlerItems.generateRightMenuElements());
        placesBoxesRecyclingView.setAdapter(adapterRightMenu);
        LinearLayoutManager rightMenuLayoutManager = new LinearLayoutManager(this);
        placesBoxesRecyclingView.setLayoutManager(rightMenuLayoutManager);

        ImageButton sprinklerButton = findViewById(R.id.main_activity__ib_sprinkler);
        sprinklerButton.setTag(R.drawable.sprinkler_on);

        sprinklerButton.setOnClickListener(v -> {
            Integer resource = (Integer) sprinklerButton.getTag();
            String toastMessage;

            if (resource == R.drawable.sprinkler_on) {
                sprinklerButton.setImageResource(R.drawable.sprinkler_off);
                sprinklerButton.setTag(R.drawable.sprinkler_off);
                toastMessage = "All tomorrows activities disabled";
                sprinklerButton.setContentDescription("Button disabled.To turn on double click");
                adapterRightMenu.setAllCheckBoxesEnabled(false);


            } else {
                sprinklerButton.setImageResource(R.drawable.sprinkler_on);
                sprinklerButton.setTag(R.drawable.sprinkler_on);
                toastMessage = "Button enabled";
                sprinklerButton.setContentDescription("Disable tomorrows watering double click");
            }

            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        });
    }

    private void updateWeatherData(){

        RecyclerView dateWeatherRecyclingView = findViewById(R.id.main_activity__rv_dateWeather);

        Objects.requireNonNull(dateWeatherRecyclingView.getAdapter()).notifyDataSetChanged();

        TextView temperatureView = findViewById(R.id.main_activity__tv_temperatureCenterLeft);
        TextView humidityView = findViewById(R.id.main_activity__tv_humidityCenterRight);
        temperatureView.setText(HandlerItems.getCurrentTemperature());
        humidityView.setText(HandlerItems.getCurrentHumidity());
    }

}

