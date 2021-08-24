package com.example.lowesabenatest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lowesabenatest.R
import com.example.lowesabenatest.databinding.WeatherDescriptionFragmentBinding
import com.example.lowesabenatest.model.WeatherData
import com.example.lowesabenatest.toFahrenheit

class WeatherDescription: Fragment() {

    companion object{
        const val EXTRA_WEATHER = "EXTRA_WEATHER"
        fun newInstance(weatherData: WeatherData): WeatherDescription{
            return WeatherDescription().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_WEATHER,weatherData)
                }
            }
        }
    }

    private var _binding: WeatherDescriptionFragmentBinding? = null
    private val binding: WeatherDescriptionFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = WeatherDescriptionFragmentBinding.inflate(inflater, container, false)
        arguments?.getParcelable<WeatherData>(EXTRA_WEATHER)?.let {
            displayWeatherDescription(it)
        }
        return binding.root
    }

    private fun displayWeatherDescription(weatherDescription: WeatherData) {
        binding.weatherDetailsTemperature.text = weatherDescription.main.temp.toFahrenheit().toString()
        binding.weatherDetailsFeelsLike.text = binding.root.context.getString(R.string.description_feels_like,
        weatherDescription.main.feels_like.toFahrenheit())
        binding.weatherDetailsWeatherType.text = weatherDescription.weather[0].main
        binding.weatherDetailsDescription.text = weatherDescription.weather[0].description
    }
}