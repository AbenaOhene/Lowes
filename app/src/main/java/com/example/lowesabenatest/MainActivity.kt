package com.example.lowesabenatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lowesabenatest.databinding.ActivityMainBinding
import com.example.lowesabenatest.view.WeatherCitySearchFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() =  _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showCitySearch()
    }

    private fun showCitySearch() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, WeatherCitySearchFragment())
            .commit()
    }
}