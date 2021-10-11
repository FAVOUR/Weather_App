package com.example.weatherapp.data.source.local

import com.example.weatherapp.data.source.local.dao.WeatherDao
import com.example.weatherapp.data.source.local.entity.WeatherEntity

class DefaultLocalDataSource(private val weatherDao: WeatherDao):LocalDataSource {
    override fun getWeatherReports(): List<WeatherEntity> {
       return weatherDao.getAllWeatherData()
    }

    override fun saveWeatherReports(allWeatherReports: List<WeatherEntity>) {
         weatherDao.insertWeatherData(weatherDataList = allWeatherReports)
    }

    override fun updateWeatherReports(allWeatherReports: List<WeatherEntity>) {
        weatherDao.updateWeatherData(weatherDataList = allWeatherReports)

    }
}