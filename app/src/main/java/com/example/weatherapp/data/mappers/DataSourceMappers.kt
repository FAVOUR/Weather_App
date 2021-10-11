package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.source.local.entity.WeatherEntity
import com.example.weatherapp.data.source.remote.model.WeatherReport
import com.example.weatherapp.ui.WeatherData

object DataSourceMappers {

    fun WeatherReport.toDataBaseModel(position: String): WeatherEntity {

        return WeatherEntity(
            city = name,
            country = sys.country,
            temp = main.temp,
            humidity = main.humidity,
            weatherId = weather[0].id,
            weatherIcon = weather[0].icon,
            weatherDescription = weather[0].description,
            windDeg = wind.deg,
            windSpeed = wind.speed
        )
    }

    fun WeatherEntity.toUiModel(): WeatherData {

        return WeatherData(
            country = country,
            city = city,
            temperature = temp,
            icon = weatherIcon,
            description = weatherDescription
        )

    }
}