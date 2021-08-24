package com.example.lowesabenatest.model

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = "65d00499677e59496ca2f318eb68c049"
    ): WeatherResponse
}