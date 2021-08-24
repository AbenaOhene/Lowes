package com.example.lowesabenatest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.lowesabenatest.databinding.WeatherCitySearchFragmentBinding
import com.example.lowesabenatest.navigateWeatherListFragment
import com.example.lowesabenatest.showError

class WeatherCitySearchFragment : Fragment() {
    private var _binding: WeatherCitySearchFragmentBinding? = null
    private val binding: WeatherCitySearchFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = WeatherCitySearchFragmentBinding.inflate(inflater, container, false)

        binding.citySearch.setOnClickListener{
            if (validateInput())
                activity?.navigateWeatherListFragment(binding.citySearchInput.editText?.text.toString())
            else
                activity?.showError("No empty city names")
        }

        return binding.root
    }

    private fun validateInput(): Boolean {
        return binding.citySearchInput.editText?.text.toString().isNotEmpty()
    }
}
