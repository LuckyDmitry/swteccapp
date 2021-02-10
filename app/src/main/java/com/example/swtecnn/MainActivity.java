package com.example.swtecnn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);


        ArrayList<DayOfWeek> dayOfWeeks = new ArrayList<>();

        dayOfWeeks.add(new DayOfWeek("February 9, 2021", "23", R.drawable.cloudy));
        dayOfWeeks.add(new DayOfWeek("February 10, 2021", "21", R.drawable.rain));

        DayOfWeekAdapter dayOfWeekAdapter = new DayOfWeekAdapter(this, R.layout.list_row, dayOfWeeks);

        listView.setAdapter(dayOfWeekAdapter);
    }
}