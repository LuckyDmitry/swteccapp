package com.swtecnn.java_threads

import android.os.Handler
import api.model.CurrentWeather
import api.model.DailyForecast
import com.example.swtecnn.HandlerItems
import com.example.swtecnn.MainActivity
import com.example.swtecnn.MyWeatherForecast
import kotlinx.coroutines.*
import java.lang.ref.WeakReference

class WeatherWithCoroutines(private val weakReference: WeakReference<MainActivity>) {

    private val scope = CoroutineScope(Job())

    fun downloadWeather() = runBlocking{

        scope.launch {
            val currentWeather = async{ getCurrentWeather() }
            val forecast = async { getWeekForecast() }

            HandlerItems.setData(currentWeather.await(), forecast.await())
            weakReference.get()?.runOnUiThread(Runnable {
                weakReference.get()?.updateWeatherData()
            })
        }
    }


    private fun getCurrentWeather(): CurrentWeather? {
        return MyWeatherForecast.getCurrentForecast()
    }

    private fun getWeekForecast(): MutableList<DailyForecast>? {
        return MyWeatherForecast.getWeekForecast()
    }


}