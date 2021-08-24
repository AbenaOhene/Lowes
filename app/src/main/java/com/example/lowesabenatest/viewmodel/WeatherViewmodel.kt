package com.example.lowesabenatest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lowesabenatest.repository.Repository
import com.example.lowesabenatest.repository.UIState
import kotlinx.coroutines.*

class WeatherViewModel(private val repository: Repository): ViewModel() {

    class WeatherViewModelProvider(private val repository: Repository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherViewModel(repository) as T
        }
    }
    private val viewModelScope = CoroutineScope(Job() + Dispatchers.IO)
    private val _weather = MutableLiveData<UIState>()
    val weather: LiveData<UIState> = _weather

    fun getWeatherByCityName(cityName: String){
        viewModelScope.launch {
            val response = repository.getWeatherData(cityName)
            withContext(Dispatchers.Main){
                _weather.value = response
            }
        }
    }
}