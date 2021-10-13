package com.example.weatherapp.data.source.local

import com.example.weatherapp.data.source.local.dao.WeatherDao
import com.example.weatherapp.data.source.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultLocalDataSource @Inject constructor(private val weatherDao: WeatherDao):LocalDataSource {
    override fun getWeatherReports(): Flow<List<WeatherEntity>> {
       return weatherDao.getAllWeatherData()
    }

    override fun saveWeatherReports(allWeatherReports: WeatherEntity) {
         weatherDao.insertWeatherData(weatherDataList = allWeatherReports)
    }

    override fun updateWeatherReports(allWeatherReports: List<WeatherEntity>) {
        weatherDao.updateWeatherData(weatherDataList = allWeatherReports)

    }

    override fun deleteAllWeatherReport() {
      weatherDao.deleteAllWeatherData()
    }
}