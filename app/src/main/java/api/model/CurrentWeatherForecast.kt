package api.model

import api.model.CurrentWeather
import api.model.WeatherImage
import com.google.gson.annotations.SerializedName

data class CurrentWeatherForecast(
        val id: Long,
        @SerializedName("main")
    val weather: CurrentWeather,
        @SerializedName("weather")
    val weatherImage: List<WeatherImage>
)
