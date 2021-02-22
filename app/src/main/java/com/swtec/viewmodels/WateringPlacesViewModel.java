package com.swtec.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.swtec.data.WateringPlace;
import com.swtec.repositories.WateringRepo;

import java.util.List;

public class WateringPlacesViewModel extends ViewModel {

    private final LiveData<List<WateringPlace>> mWateringPlaces;

    private final WateringRepo mWateringRepo = new WateringRepo();

    public WateringPlacesViewModel() {
        Log.d("WateringPlacesViewModel", "Constructor");
        mWateringPlaces = mWateringRepo.getWateringPlaces();
    }

    public LiveData<List<WateringPlace>> getWateringPlaces() {
        return mWateringPlaces;
    }

    public void setWaterPlaces(List<WateringPlace> places) {
        mWateringRepo.setPlaces(places);
    }

}
