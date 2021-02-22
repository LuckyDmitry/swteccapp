package com.swtec.repositories;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.swtec.data.WateringPlace;
import com.swtec.data.Weather;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.sync.Mutex;

public class WateringRepo {


    private MutableLiveData<List<WateringPlace>> mWateringPlaces = new MutableLiveData<>();
    private List<WateringPlace> wateringPlaces = new ArrayList<>();

    public LiveData<List<WateringPlace>> getWateringPlaces() {

        return mWateringPlaces;
    }

    public void insertPlace(WateringPlace place){
        wateringPlaces.add(place);
        mWateringPlaces.setValue(wateringPlaces);
    }
}
