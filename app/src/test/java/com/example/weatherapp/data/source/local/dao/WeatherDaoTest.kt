package com.example.weatherapp.data.source.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapp.ServerAndDbSetup
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDaoTest:ServerAndDbSetup() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    @Throws(Exception::class)
    fun getAllWeatherData_returnASingleList()= runBlocking {

            //When the data is fetched form the database
            val weatherData = weatherDao.getAllWeatherData().first()

            //Assert the size of the list is 1
            assertThat(weatherData.size, equalTo(1))

            //Assert that the counrty is Ke
            assertThat(weatherData[0].country, equalTo("Ke"))
        }


    @Test
    @Throws(Exception::class)
    fun getAllWeatherData_returnACountry()= runBlocking {

        //When the data is fetched form the database
        val weatherData = weatherDao.getAllWeatherData().first()

        //Assert that the counrty is Ke
        assertThat(weatherData[0].country, equalTo("Ke"))
    }



}