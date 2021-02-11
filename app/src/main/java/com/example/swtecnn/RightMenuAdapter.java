package com.example.swtecnn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RightMenuAdapter extends ArrayAdapter<RigthMenuElement> {

    private Context context;
    private int mResources;

    public RightMenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<RigthMenuElement> objects) {
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

        CheckBox checkBox = convertView.findViewById(R.id.main_activity__placeCheckBox);
        TextView textView = convertView.findViewById(R.id.main_activity__placeTextView);

        textView.setText(getItem(position).getPlace());
        checkBox.setChecked(true);

        return convertView;

    }
}
