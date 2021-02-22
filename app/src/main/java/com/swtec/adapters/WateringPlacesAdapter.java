package com.swtec.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swtecnn.R;
import com.swtec.data.WateringPlace;

import java.util.List;

public class WateringPlacesAdapter extends RecyclerView.Adapter<WateringPlacesAdapter.MyViewHolder> {


    List<WateringPlace> mWateringPlaces;
    LayoutInflater mLayoutInflater;

    public WateringPlacesAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_watering_places, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(mWateringPlaces != null){
            WateringPlace wateringPlace = mWateringPlaces.get(position);
            holder.mPlaceTextView.setText(wateringPlace.getPlaceName());
            holder.mRightRadioButton.setEnabled(wateringPlace.isRadioButtonChecked());
            holder.mRightRadioButton.setChecked(wateringPlace.isRadioButtonChecked());
            holder.mLeftCheckBox.setChecked(wateringPlace.isCheckBoxChecked());
        }
    }

    @Override
    public int getItemCount() {
        if(mWateringPlaces == null){
            return 0;
        }
        return mWateringPlaces.size();
    }

    public void turnOffCheckBoxes(){
        for(int i = 0;i < getItemCount();++i){
            mWateringPlaces.get(i).setIsCheckBoxChecked(false);
        }
        notifyDataSetChanged();
    }

    public void setWateringPlaces(List<WateringPlace> mWateringPlaces) {
        this.mWateringPlaces = mWateringPlaces;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView mPlaceTextView;
        private final CheckBox mLeftCheckBox;
        private final RadioButton mRightRadioButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mPlaceTextView = itemView.findViewById(R.id.item_watering_places__tv_place);
            mLeftCheckBox = itemView.findViewById(R.id.item_watering_places__cb_left);
            mRightRadioButton = itemView.findViewById(R.id.item_watering_places__rb_right);
        }
    }
}
