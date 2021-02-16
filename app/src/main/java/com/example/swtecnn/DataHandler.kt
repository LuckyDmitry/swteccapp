package com.example.swtecnn

import android.util.Log
import api.RetrofitClient

class DataHandler : Runnable {
    override fun run() {
        Log.d("Trifonov","Before");
        val currentWeather = RetrofitClient.getCurrentWeather().execute().body();
        Log.d("Trifonov", currentWeather?.weather?.temp.toString());
    }

}