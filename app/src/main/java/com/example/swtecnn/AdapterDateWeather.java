package com.example.swtecnn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDateWeather extends RecyclerView.Adapter<AdapterDateWeather.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(DateWeather content);
    }
    private final OnItemClickListener listener;
    private ArrayList<DateWeather> dateWeatherList = new ArrayList<>();
    private View.OnClickListener clickListener;

    public AdapterDateWeather(ArrayList<DateWeather> dateWeathers, OnItemClickListener onItemClickListener) {
        dateWeatherList = dateWeathers;
        listener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date_weather, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dateWeatherList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dateWeatherList.size();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView dateView;
        private final TextView tempView;
        private final ImageView weatherView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.item_date_weather__tv_dateWeather);
            tempView = itemView.findViewById(R.id.item_date_weather__tv_temperature);
            weatherView = itemView.findViewById(R.id.item_date_weather__iv_weatherPicture);
        }

        public void bind(@NonNull DateWeather dateWeather, final OnItemClickListener listener){

            dateView.setText(dateWeather.getDate());
            tempView.setText(dateWeather.getTemperature());
            weatherView.setImageResource(dateWeather.getWeatherImage());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(dateWeather);
                }
            });
        }
    }
}
