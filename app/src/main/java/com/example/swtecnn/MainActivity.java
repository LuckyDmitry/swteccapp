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

import java.lang.ref.WeakReference;
import java.util.Objects;

public class MainActivity extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    MyAsyncTask myAsyncTask;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        RecyclerView dateWeatherRecyclingView = findViewById(R.id.main_activity__rv_dateWeather);

        if(savedInstanceState != null){
            myAsyncTask = (MyAsyncTask) getLastNonConfigurationInstance();
        }
        if(savedInstanceState == null){
            myAsyncTask = new MyAsyncTask(new WeakReference<>(MainActivity.this),
                    ((currentWeather, weekForecast) -> {
                        updateWeatherData();
                        this.runOnUiThread(() -> HandlerItems.setData(currentWeather, weekForecast));
                    }));
            myAsyncTask.execute();
        }

        downloadWeather();
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

    private void downloadWeather(){
        MyAsyncTask myAsyncTask = new MyAsyncTask(new WeakReference<>(MainActivity.this),
                ((currentWeather, weekForecast) -> {
                    updateWeatherData();
                    this.runOnUiThread(() -> HandlerItems.setData(currentWeather, weekForecast));
                }));
        myAsyncTask.execute();
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

