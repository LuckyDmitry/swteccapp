package com.kotlin.coroutines_homework

import android.view.View
import android.widget.TextView
import api.model.CurrentWeather
import api.model.DailyForecast
import api.model.WeatherForecast
import com.example.swtecnn.HandlerItems
import com.example.swtecnn.MainActivity
import kotlinx.coroutines.*
import kotlin.coroutines.*
import com.example.swtecnn.MyWeatherForecast
import okhttp3.internal.wait
import java.util.concurrent.CompletableFuture

val scope = CoroutineScope(Job())

fun coroutinesSetData(temp: TextView, hum: TextView) = runBlocking{
    scope.launch(Dispatchers.IO) {
        val currentWeather = async { getWeather() }
        val dailyForecast = async { getWeekForecast() }

        HandlerItems.setData(currentWeather.await(), dailyForecast.await())
        temp.text =  HandlerItems.getCurrentTemperature()
        hum.text = HandlerItems.getCurrentHumidity()
    }.join()
}

private fun getWeather(): CurrentWeather? {
    return MyWeatherForecast.getCurrentForecast()
}

private fun getWeekForecast(): List<DailyForecast>? {
    return MyWeatherForecast.getWeekForecast()
}
