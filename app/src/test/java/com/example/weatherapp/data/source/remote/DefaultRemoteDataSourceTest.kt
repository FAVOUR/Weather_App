package com.example.weatherapp.data.source.remote

import com.example.weatherapp.KENYA
import com.example.weatherapp.ServerAndDbSetup
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Test


class DefaultRemoteDataSourceTest: ServerAndDbSetup() {

    //TODO NAME THE TEST AS EITHER SMALL OR LARGE TEST

    @Test
    fun rightCity_returnsASuccessfulResponse() =
        runBlocking {

            //Given a remote dataSource
            val remoteDataSource = DefaultRemoteDataSource(weatherService)

            //When the city is not empty
            val weatherResponse = remoteDataSource.getWeatherData(city = KENYA)

            //Then the  response code is 200
            assertEquals(weatherResponse.code(), 200)

            //Then the response is not null
            MatcherAssert.assertThat(weatherResponse, CoreMatchers.not(null))

            //Then the name returned is kenya
            assertEquals(weatherResponse.body()?.name, KENYA)


        }

    @Test
    fun wrongCity_returnsAFailedResponse() =
        runBlocking {

            //Given a remote dataSource
            val remoteDataSource = DefaultRemoteDataSource(weatherService)

            //When the city is not empty
            val weatherResponse = remoteDataSource.getWeatherData(city = "")

            //Then the response code is not 200
            assertNotEquals(weatherResponse.code(), 200)

            //Then tha response is not null
            MatcherAssert.assertThat(weatherResponse, CoreMatchers.not(null))

            //Then name returned is null
            assertEquals(weatherResponse.body()?.name, null)

        }


}