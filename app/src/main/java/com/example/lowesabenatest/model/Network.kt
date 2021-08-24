package com.example.lowesabenatest.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: WeatherApi = retrofit.create(WeatherApi::class.java)
}