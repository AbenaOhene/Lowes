package com.example.lowesabenatest.repository

import com.example.lowesabenatest.model.WeatherApi
import com.example.lowesabenatest.model.WeatherResponse

class RepositoryImpl(private val api: WeatherApi): Repository {

    override suspend fun getWeatherData(cityName: String): UIState {
        val response = api.getWeather(cityName)

        return createUIState(response)
    }

    private fun createUIState(response: WeatherResponse?): UIState {
        return if (response != null)
            UIState.Response(response)
        else
            UIState.Error("Error message")
    }
}