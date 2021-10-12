package com.example.weatherapp.data.repository

import com.example.weatherapp.data.source.local.LocalDataSource
import com.example.weatherapp.data.source.local.entity.WeatherEntity
import com.example.weatherapp.data.source.remote.RemoteDataSource
import com.example.weatherapp.data.source.remote.helper.ResponseFromServer
import com.example.weatherapp.data.source.remote.helper.SafeApiCall
import com.example.weatherapp.data.source.remote.model.WeatherReport
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DefaultWeatherRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : WeatherRepository {

    override fun getCurrentWeather(): Flow<List<WeatherEntity>> {

        return localDataSource.getWeatherReports()
    }

    @FlowPreview
    override fun fetchWeatherData(listOfCities: List<String>): Flow<ResponseFromServer<WeatherReport>> {
        return listOfCities.asFlow()
            .flatMapMerge {
                flow {
                    val request: SafeApiCall<WeatherReport> =
                        SafeApiCall(networkRequest = {
                            remoteDataSource.getCurrentDataResource(
                                city = it
                            )
                        })

                    emit(request.makeApiNetworkRequest())
                }
            }
    }

    override fun clearWeatherData() {
        localDataSource.deleteAllWeatherReport()
    }

    override fun insertWeatherData(allWeatherResult: WeatherEntity) {
        localDataSource.getWeatherReports()
    }


}