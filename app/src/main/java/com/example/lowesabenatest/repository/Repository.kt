package com.example.lowesabenatest.repository

interface Repository {
    suspend fun getWeatherData(cityName: String): UIState
}