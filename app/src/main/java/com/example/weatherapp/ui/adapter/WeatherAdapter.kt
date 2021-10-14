package com.example.weatherapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemWeatherBinding
import com.example.weatherapp.ui.model.WeatherData
import com.example.weatherapp.ui.util.Extensions.formatTemperature
import com.example.weatherapp.ui.util.Extensions.getImageBasedOnLandMark
import com.example.weatherapp.ui.util.Extensions.getLocation
import com.example.weatherapp.ui.util.Extensions.setWeatherConditionImage

class WeatherAdapter(private val onClickListener: (WeatherData, View) -> Unit) :
    ListAdapter<WeatherData, WeatherViewHolder>(WeatherDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val view = ListItemWeatherBinding.inflate(inflater, parent, false)

        return WeatherViewHolder(binding = view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherData = getItem(position)
        holder.bind(weatherData)

        holder.itemView.setOnClickListener {
            onClickListener(weatherData, it)
        }

    }
}

class WeatherViewHolder(private val binding: ListItemWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WeatherData) {
        binding.landmarkPhoto.setImageDrawable(ContextCompat.getDrawable(binding.root.context,getImageBasedOnLandMark(city = item.city)))

        setWeatherConditionImage(
            imageView = binding.weatherIconIv,
            weatherIdentifier = item.weatherConditionIdentifier,
        )

        formatTemperature(item.temperature).also { binding.temperatureTv.text = it }

        binding.weatherDescTv.text = item.description

        getLocation(item).also { binding.cityAndCountryTv.text = it }
    }




}


private class WeatherDiffUtil : DiffUtil.ItemCallback<WeatherData>() {
    override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
        return oldItem.city == newItem.city
    }

    override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
        return oldItem == newItem
    }

}

