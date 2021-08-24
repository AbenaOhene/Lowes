package com.example.lowesabenatest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lowesabenatest.databinding.WeatherListFragmentBinding
import com.example.lowesabenatest.isLoading
import com.example.lowesabenatest.model.Network
import com.example.lowesabenatest.model.WeatherData
import com.example.lowesabenatest.navigateWeatherDescription
import com.example.lowesabenatest.repository.Repository
import com.example.lowesabenatest.repository.RepositoryImpl
import com.example.lowesabenatest.repository.UIState
import com.example.lowesabenatest.showError
import com.example.lowesabenatest.viewmodel.WeatherViewModel

class WeatherListFragment: Fragment() {
    companion object {
        const val EXTRA_CITY_NAME = "EXTRA_CITY_NAME"
        fun newInstance(cityName: String): WeatherListFragment {
            val args = Bundle()
                .apply {
                    putString(EXTRA_CITY_NAME, cityName)
                }
            val fragment = WeatherListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: WeatherListFragmentBinding? = null
    private val binding: WeatherListFragmentBinding get() = _binding!!
    private val viewModel: WeatherViewModel by lazy {
        WeatherViewModel.WeatherViewModelProvider(repository).create(WeatherViewModel::class.java)
    }
    private val repository: Repository by lazy {
        RepositoryImpl(Network.api)
    }
    private val adapter: WeatherListAdapter by lazy {
        WeatherListAdapter(::navigateWeatherDescription)
    }

    private fun navigateWeatherDescription(weatherData: WeatherData) {
        activity?.navigateWeatherDescription(weatherData)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = WeatherListFragmentBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.weather.observe(viewLifecycleOwner){
            processUIState(it)
        }

        arguments?.getString(EXTRA_CITY_NAME)?.let{
            viewModel.getWeatherByCityName(it)
        }

        return binding.root
    }

    private fun processUIState(dataState: UIState) {
        when (dataState){
            is UIState.Response -> adapter.submitList(dataState.data.list)
            is UIState.Error -> activity?.showError(dataState.error)
            is UIState.Loading -> activity?.isLoading(dataState.isLoading)
        }
    }
}