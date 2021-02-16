package com.example.swtecnn;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import api.RetrofitClient;
import api.RetrofitClientKt;
import api.RetrofitService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import smart.sprinkler.app.api.model.CurrentWeather;
import smart.sprinkler.app.api.model.CurrentWeatherForecast;
import smart.sprinkler.app.api.model.DailyForecast;
import smart.sprinkler.app.api.model.WeatherForecast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        RecyclerView dateWeatherRecyclingView = findViewById(R.id.main_activity__rv_dateWeather);
        RecyclerView placesBoxesRecyclingView = findViewById(R.id.main_activity__rv_placesBoxes);

        AdapterDateWeather adapterDateWeather = new AdapterDateWeather(generateDatesWeather(), content -> {
            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putExtra(EXTRA_MESSAGE, content);
            startActivity(intent);
        });

        dateWeatherRecyclingView.setAdapter(adapterDateWeather);
        LinearLayoutManager dateWeatherLayoutManager = new LinearLayoutManager(this);
        dateWeatherRecyclingView.setLayoutManager(dateWeatherLayoutManager);

        AdapterRightMenu adapterRightMenu = new AdapterRightMenu(generateRightMenuElements());
        placesBoxesRecyclingView.setAdapter(adapterRightMenu);
        LinearLayoutManager rightMenuLayoutManager = new LinearLayoutManager(this);
        placesBoxesRecyclingView.setLayoutManager(rightMenuLayoutManager);

        ImageButton sprinklerButton = findViewById(R.id.main_activity__ib_sprinkler);
        sprinklerButton.setTag(R.drawable.sprinkler_on);

        sprinklerButton.setOnClickListener(v -> {
            Integer resource = (Integer)sprinklerButton.getTag();
            String toastMessage;
            if(resource == R.drawable.sprinkler_on){
                sprinklerButton.setImageResource(R.drawable.sprinkler_off);
                sprinklerButton.setTag(R.drawable.sprinkler_off);
                toastMessage = "All tomorrows activities disabled";
                sprinklerButton.setContentDescription("Button disabled.To turn on double click");
                adapterRightMenu.setAllCheckBoxesEnabled(false);

            }
            else{
                sprinklerButton.setImageResource(R.drawable.sprinkler_on);
                sprinklerButton.setTag(R.drawable.sprinkler_on);
                toastMessage = "Button enabled";
                sprinklerButton.setContentDescription("Disable tomorrows watering double click");
            }

            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        });

        TextView temperatureView = findViewById(R.id.main_activity__tv_temperatureCenterLeft);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CurrentWeatherForecast currentWeather = RetrofitClient.INSTANCE.getCurrentWeather().execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private ArrayList<DateWeather> generateDatesWeather(){
        ArrayList<DateWeather> dayOfWeeks = new ArrayList<>();
        for(int i = 0;i < 10; ++i) {
            dayOfWeeks.add(new DateWeather("February " + i + ", 2021", "23 \u2103", R.drawable.cloudy));
        }
        return dayOfWeeks;
    }

    private ArrayList<RightMenuElement> generateRightMenuElements(){
        ArrayList<RightMenuElement> rightMenuBar = new ArrayList<>();
        rightMenuBar.add(new RightMenuElement("Backyard", false, false, false));
        rightMenuBar.add(new RightMenuElement("Back Patio", false, false,false));
        rightMenuBar.add(new RightMenuElement("Front Yard", false, false, false));
        rightMenuBar.add(new RightMenuElement("Garden", false, true, true));
        rightMenuBar.add(new RightMenuElement("Porch", false, false, false));
        return rightMenuBar;
    }

}