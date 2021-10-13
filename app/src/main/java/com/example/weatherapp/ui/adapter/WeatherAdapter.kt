package com.example.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemWeatherBinding
import com.example.weatherapp.ui.model.WeatherData
import com.example.weatherapp.ui.util.Extensions.TEMPERATURE_DEGREE
import com.example.weatherapp.ui.util.Extensions.getImageBasedOnLandMark
import com.example.weatherapp.ui.util.Extensions.setWeatherConditionImage

class WeatherAdapter : ListAdapter<WeatherData, WeatherViewHolder>(WeatherDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val view = ListItemWeatherBinding.inflate(inflater, parent, false)

        return WeatherViewHolder(binding = view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {

        holder.bind(getItem(position))

    }
}

 class WeatherViewHolder(private val binding: ListItemWeatherBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(item:WeatherData){
        binding.landmarkPhoto.setBackgroundResource(getImageBasedOnLandMark(city = item.city))

        setWeatherConditionImage(imageView = binding.weatherIconIv ,icon =item.icon,defaultImage = R.drawable.ic_unknown)

        "${item.temperature}$TEMPERATURE_DEGREE".also { binding.temperatureTv.text = it }

        binding.weatherDescTv .text=item.description

        "${ item.city }, ${item.country}".also { binding.cityAndCountryTv.text = it }
    }
}



private class WeatherDiffUtil() : DiffUtil.ItemCallback<WeatherData>() {
    override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
        return oldItem.city == newItem.city
    }

    override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
        return oldItem == newItem
    }

}

