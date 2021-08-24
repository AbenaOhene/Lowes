package com.example.lowesabenatest

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.lowesabenatest.model.WeatherData
import com.example.lowesabenatest.view.WeatherDescription
import com.example.lowesabenatest.view.WeatherListFragment

fun Double.toFahrenheit(): Int {
    return ((this - 273.15) * 9 / 5 + 32).toInt()
}

fun FragmentActivity?.navigateWeatherListFragment(cityName: String) {
    this?.supportFragmentManager?.beginTransaction()
        ?.replace(R.id.fragment_container, WeatherListFragment.newInstance(cityName))
        ?.commit()
}

fun FragmentActivity?.navigateWeatherDescription(weatherData: WeatherData) {
    this?.supportFragmentManager?.beginTransaction()
        ?.replace(R.id.fragment_container, WeatherDescription.newInstance(weatherData))
        ?.addToBackStack(null)
        ?.commit()
}

fun FragmentActivity?.showError(errorMessage: String) {
    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
}

fun FragmentActivity?.isLoading(isLoading: Boolean){
    Toast.makeText(this, isLoading.toString(), Toast.LENGTH_SHORT).show()
}