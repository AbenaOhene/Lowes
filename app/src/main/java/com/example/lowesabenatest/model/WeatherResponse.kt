package com.example.lowesabenatest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class WeatherResponse(
    val city: City,
    val list: List<WeatherData>
)

data class City(
    val name: String
)

@Parcelize
data class WeatherData(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Float,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
): Parcelable

@Parcelize
data class Clouds(
    val all: Int
): Parcelable

@Parcelize
data class Main(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
): Parcelable

@Parcelize
data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
): Parcelable

@Parcelize
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
): Parcelable
