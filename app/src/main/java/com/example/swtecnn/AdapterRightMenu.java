package com.example.swtecnn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRightMenu extends RecyclerView.Adapter<AdapterRightMenu.RightMenuViewHolder> {


    private ArrayList<RightMenuElement> rightMenuElements = new ArrayList<>();
    private final ArrayList<RightMenuViewHolder> rightMenuViewHolders = new ArrayList<>();

    AdapterRightMenu(ArrayList<RightMenuElement> rightMenuElements){
        this.rightMenuElements = rightMenuElements;
    }

    @NonNull
    @Override
    public RightMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_places_boxes, parent, false);
        return new RightMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RightMenuViewHolder holder, int position) {
        holder.bind(this.rightMenuElements.get(position));
    }

    @Override
    public int getItemCount() {
        return rightMenuElements.size();
    }

    static final class RightMenuViewHolder extends RecyclerView.ViewHolder{

        private final TextView placeView;
        private final CheckBox checkboxView;
        private final RadioButton radioButtonView;

        RightMenuViewHolder(@NonNull View viewHolder){
            super(viewHolder);
            placeView = viewHolder.findViewById(R.id.item_places_boxes__tv_place);
            checkboxView = viewHolder.findViewById(R.id.item_places_boxes__cb_left);
            radioButtonView = viewHolder.findViewById(R.id.item_places_boxes__rb_right);
        }

        private void bind(@NonNull RightMenuElement menuElement){
            placeView.setText(menuElement.getPlace());
            checkboxView.setChecked(menuElement.isCheckBoxChecked());
            radioButtonView.setChecked(menuElement.isRadioButtonChecked());
            radioButtonView.setEnabled(menuElement.isRadioButtonEnabled());
            if(menuElement.isRadioButtonChecked()){
                placeView.setTextColor(placeView.getResources().getColor(R.color.green));
            }
        }
    }

    public void setAllCheckBoxesEnabled(boolean flag){
        for (int i = 0;i < this.rightMenuElements.size();++i){
            rightMenuElements.get(i).setCheckBoxChecked(false);
        }
        notifyDataSetChanged();
    }
}
