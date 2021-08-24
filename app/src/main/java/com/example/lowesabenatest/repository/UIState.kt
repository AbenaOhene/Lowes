package com.example.lowesabenatest.repository

import com.example.lowesabenatest.model.WeatherResponse

sealed class UIState{
    data class Response(val data: WeatherResponse): UIState()
    data class Error(val error: String): UIState()
    data class Loading(val isLoading: Boolean): UIState()
}