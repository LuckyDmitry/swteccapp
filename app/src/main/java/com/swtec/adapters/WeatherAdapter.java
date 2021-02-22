package com.swtec.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swtecnn.R;
import com.swtec.data.Weather;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private List<Weather> mListWeather;

    public WeatherAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_date_weather, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(mListWeather != null){
            Weather weather = mListWeather.get(position);
            holder.mTemperatureTextView.setText(weather.getTemperature());
            holder.mDateTextView.setText(weather.getDate());
        }
        else{
            holder.mDateTextView.setText("Unknown");
        }
    }

    public void setWeather(List<Weather> weather){
        mListWeather = weather;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mListWeather == null){
            return 0;
        }
        return mListWeather.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView mTemperatureTextView;
        private final TextView mDateTextView;
        private final ImageView mWeatherImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDateTextView = itemView.findViewById(R.id.item_date_weather__tv_dateWeather);
            mTemperatureTextView = itemView.findViewById(R.id.item_date_weather__tv_temperature);
            mWeatherImageView = itemView.findViewById(R.id.item_date_weather__iv_weatherPicture);
        }
    }


}
