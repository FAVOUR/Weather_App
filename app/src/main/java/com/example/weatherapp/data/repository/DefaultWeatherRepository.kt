package com.example.weatherapp.data.repository

import com.example.weatherapp.data.source.local.LocalDataSource
import com.example.weatherapp.data.source.local.entity.WeatherEntity
import com.example.weatherapp.data.source.remote.RemoteDataSource
import com.example.weatherapp.data.source.remote.helper.ResponseFromServer
import com.example.weatherapp.data.source.remote.helper.SafeApiCall
import com.example.weatherapp.data.source.remote.model.WeatherReport
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class DefaultWeatherRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val dispatchers: CoroutineContext
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
                            remoteDataSource.getWeatherData(
                                city = it
                            )
                        })

                    emit(request.makeApiNetworkRequest())
                }
            }.flowOn(dispatchers)
    }

    override suspend fun clearWeatherData() {
        withContext(dispatchers) {
            localDataSource.deleteAllWeatherReport()
        }
    }


    override suspend fun insertWeatherData(allWeatherResult: WeatherEntity) {
        withContext(dispatchers) {
            localDataSource.saveWeatherReports(allWeatherResult)
        }
    }


}