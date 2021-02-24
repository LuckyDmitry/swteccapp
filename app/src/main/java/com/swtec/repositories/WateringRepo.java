package com.swtec.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.swtec.data.WateringPlace;

import java.util.ArrayList;
import java.util.List;

public class WateringRepo {

    private final MutableLiveData<List<WateringPlace>> mWateringPlaces = new MutableLiveData<>();

    // TODO : Rework into Room
    private final List<WateringPlace> wateringPlaces = new ArrayList<>();

    public WateringRepo() {
        // Prepopulate
        setPlaces(getHardCodePlaces());
    }

    public void setPlaces(List<WateringPlace> places) {
        wateringPlaces.addAll(places);
        mWateringPlaces.setValue(wateringPlaces);
    }

    public LiveData<List<WateringPlace>> getWateringPlaces() {
        return mWateringPlaces;
    }

    private List<WateringPlace> getHardCodePlaces(){
        ArrayList<WateringPlace> places = new ArrayList<>();
        places.add(new WateringPlace("Backyard", false, false));
        places.add(new WateringPlace("Front yard", false, false));
        places.add(new WateringPlace("Porch", false, true));
        places.add(new WateringPlace("Garden", false, false));
        places.add(new WateringPlace("Back patio", false, false));
        return places;
    }
}
