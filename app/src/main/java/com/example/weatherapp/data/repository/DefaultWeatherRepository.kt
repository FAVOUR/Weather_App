package com.example.weatherapp.data.repository

import com.example.weatherapp.data.mappers.DataSourceMappers.toDataBaseModel
import com.example.weatherapp.data.mappers.DataSourceMappers.toUiModel
import com.example.weatherapp.data.source.local.LocalDataSource
import com.example.weatherapp.data.source.local.entity.WeatherEntity
import com.example.weatherapp.data.source.remote.RemoteDataSource
import com.example.weatherapp.data.source.remote.model.WeatherReport
import com.example.weatherapp.ui.WeatherData
import kotlinx.coroutines.flow.*

class DefaultWeatherRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : WeatherRepository {

    override suspend fun getCurrentWeather(listOfCities:List<String>): Flow<WeatherEntity> {
       return  fetchWeatherData(listOfCities)
           .map {
               it.toDataBaseModel()
           }

    }

    fun fetchWeatherData(listOfCities:List<String>):Flow<WeatherReport>{
        return  listOfCities.asFlow()
            .flatMapMerge {
                flow{ emit(remoteDataSource.getCurrentDataResource(it)) }
            }
    }

    override fun clearWeatherData() {
        localDataSource.getWeatherReports()
    }

    override fun insertWeatherData(allWeatherResult: WeatherEntity) {

    }


}