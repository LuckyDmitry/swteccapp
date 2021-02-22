package com.swtec.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.swtec.data.WateringPlace;
import com.swtec.repositories.WateringRepo;

import java.util.ArrayList;
import java.util.List;

public class WateringPlacesViewModel extends ViewModel {

    private LiveData<List<WateringPlace>> mWateringPlaces;
    private WateringRepo mWateringRepo;

    public WateringPlacesViewModel(){
        mWateringRepo = new WateringRepo();
        mWateringPlaces = mWateringRepo.getWateringPlaces();
        Log.d("WateringPlacesViewModel", "Constructor1");
        if(mWateringPlaces.getValue() == null){
            Log.d("WateringPlacesViewModel", "Constructor");
            for (WateringPlace place:
                 getHardCodePlaces()) {
                this.insertPlace(place);
            }
        }
    }

    public LiveData<List<WateringPlace>> getWateringPlaces() {
        return mWateringPlaces;
    }

    public void insertPlace(WateringPlace place){
        Log.d("WateringPlacesViewModel", "insertPlace");
        mWateringRepo.insertPlace(place);
    }

    private List<WateringPlace> getHardCodePlaces(){
        ArrayList<WateringPlace> places = new ArrayList<>();
        places.add(new WateringPlace("Garden", false, false));
        places.add(new WateringPlace("Barden", false, false));
        places.add(new WateringPlace("sadsa", false, true));
        places.add(new WateringPlace("12321", false, false));
        return places;
    }
}
