package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.source.local.entity.WeatherEntity
import com.example.weatherapp.data.source.remote.model.WeatherReport
import com.example.weatherapp.ui.model.CoreWeatherDetails
import com.example.weatherapp.ui.model.WeatherData

object DataSourceMappers {

    fun WeatherReport.toDataBaseModel(): WeatherEntity {

        return WeatherEntity(
            city = name,
            country = sys.country,
            temp = main.temp,
            humidity = main.humidity,
            weatherId = weather[0].id,
            weatherIcon = weather[0].icon,
            weatherDescription = weather[0].description,
            windDeg = wind.deg,
            windSpeed = wind.speed,
            feelsLike = main.feelsLike,
            minTemperature = main.tempMin,
            maxTemperature = main.tempMax,
            pressure = main.pressure

        )
    }

    fun WeatherEntity.toUiModel(): WeatherData {
        return WeatherData(
            country = country,
            city = city,
            temperature = temp,
            weatherConditionIdentifier = weatherIcon,
            description = weatherDescription,
            coreWeatherDetails = toCoreWeatherDetails()
        )

    }

    private fun WeatherEntity.toCoreWeatherDetails(): CoreWeatherDetails {

        return CoreWeatherDetails(
            feelsLike,
            minTemperature,
            maxTemperature,
            pressure,
            humidity,
            windSpeed
        )
    }

}