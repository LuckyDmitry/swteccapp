package com.example.swtecnn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DayOfWeekAdapter extends ArrayAdapter<DayOfWeek> {

    private Context context;
    private int mResources;


    public DayOfWeekAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DayOfWeek> objects) {
        super(context, resource, objects);
        this.context = context;
        this.mResources = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView = layoutInflater.inflate(this.mResources, parent, false);

        ImageView imageView = convertView.findViewById(R.id.weatherImageView);
        TextView firstView = convertView.findViewById(R.id.temperatureTextView);
        TextView secondView = convertView.findViewById(R.id.dateTextView);

        imageView.setImageResource(getItem(position).getWeatherImage());
        secondView.setText(getItem(position).getDate());

        firstView.setText(getItem(position).getTemperature());

        return convertView;
    }
}
