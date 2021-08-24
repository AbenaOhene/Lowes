package com.example.lowesabenatest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesabenatest.BuildConfig
import com.example.lowesabenatest.R
import com.example.lowesabenatest.databinding.WeatherItemLayoutBinding
import com.example.lowesabenatest.model.WeatherData
import com.example.lowesabenatest.toFahrenheit

class WeatherListAdapter(private val callback: (WeatherData) -> Unit):
    ListAdapter<WeatherData, WeatherListAdapter.WeatherListViewHolder>(DiffUtil){

    class WeatherListViewHolder(private val binding: WeatherItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(weatherItem: WeatherData, callback: (WeatherData) -> Unit){
            binding.weatherType.text = weatherItem.weather[0].main
            binding.weatherTemperature.text = binding.root.context.getString(
                R.string.weather_temp,
                    weatherItem.main.temp.toFahrenheit())
            binding.root.setOnClickListener { callback(weatherItem) }
        }
    }

    object DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<WeatherData>(){
        override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        return WeatherListViewHolder(
            WeatherItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.onBind(currentList[position], callback)
    }
}