package com.example.swtecnn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView dateWeatherListView;
    ListView placesBoxesListView;
    ArrayList<DayOfWeek> dayOfWeeks;
    ArrayList<RigthMenuElement> rightMenuBar;
    ImageButton sprinklerOnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateWeatherListView = findViewById(R.id.main_activity__dateAndWeatherListView);
        placesBoxesListView = findViewById(R.id.main_activity__placesAndCheckBoxesListView);


        dayOfWeeks = new ArrayList<>();
        rightMenuBar = new ArrayList<>();


        sprinklerOnButton = findViewById(R.id.main_activity__sprinklerOnIB);

        dayOfWeeks.add(new DayOfWeek("February 9, 2021", "23", R.drawable.cloudy));
        dayOfWeeks.add(new DayOfWeek("February 10, 2021", "21", R.drawable.rain));
        dayOfWeeks.add(new DayOfWeek("February 11, 2021", "12", R.drawable.partly_cloudy));

        rightMenuBar.add(new RigthMenuElement("Backyard", new CheckBox(getApplicationContext())));
        rightMenuBar.add(new RigthMenuElement("Back Patio", new CheckBox(getApplicationContext())));
        rightMenuBar.add(new RigthMenuElement("Front Yard", new CheckBox(getApplicationContext())));
        rightMenuBar.add(new RigthMenuElement("Garden", new CheckBox(getApplicationContext())));
        rightMenuBar.add(new RigthMenuElement("Porch", new CheckBox(getApplicationContext())));



        DayOfWeekAdapter dayOfWeekAdapter = new DayOfWeekAdapter(this, R.layout.date_weather_list, dayOfWeeks);

        RightMenuAdapter rightMenuAdapter = new RightMenuAdapter(this, R.layout.places_boxes_list, rightMenuBar);

        dateWeatherListView.setAdapter(dayOfWeekAdapter);
        placesBoxesListView.setAdapter(rightMenuAdapter);

        sprinklerOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprinklerOnButton.setImageResource(R.drawable.sprinkler_off);

                for(RigthMenuElement element: rightMenuBar){
                    element.setCheckBox(false);
                    element.setPlace("MyWord");

                }
                rightMenuAdapter.notifyDataSetChanged();
            }
        });
    }

}