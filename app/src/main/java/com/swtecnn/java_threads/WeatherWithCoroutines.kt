package com.swtecnn.java_threads

import android.os.Handler
import api.model.CurrentWeather
import api.model.DailyForecast
import com.example.swtecnn.HandlerItems
import com.example.swtecnn.MainActivity
import com.example.swtecnn.MyWeatherForecast
import kotlinx.coroutines.*
import okhttp3.internal.wait
import java.lang.ref.WeakReference

class WeatherWithCoroutines(private val weakReference: WeakReference<MainActivity>) {

    private val scope = CoroutineScope(Job())

    fun downloadWeather(){

        scope.launch {
            val currentWeather = getCurrentWeather()
            val forecast = getWeekForecast()

            HandlerItems.setData(currentWeather.await(), forecast.await())
            weakReference.get()?.runOnUiThread(Runnable {
                weakReference.get()?.updateWeatherData()
            })
        }
    }

    private fun getCurrentWeather(): Deferred<CurrentWeather> {
        return scope.async(Dispatchers.IO){
            MyWeatherForecast.getCurrentForecast()
        }
    }

    private fun getWeekForecast(): Deferred<MutableList<DailyForecast>> {
        return scope.async(Dispatchers.IO){
            MyWeatherForecast.getWeekForecast()
        }
    }


}