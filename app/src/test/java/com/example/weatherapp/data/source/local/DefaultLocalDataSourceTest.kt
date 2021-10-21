package com.example.weatherapp.data.source.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.KENYA
import com.example.weatherapp.data.source.local.dao.WeatherDao
import com.example.weatherapp.data.source.local.db.WeatherDatabase
import com.example.weatherapp.data.source.local.entity.WeatherEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//@RunWith(RobolectricTestRunner::class)

@RunWith(AndroidJUnit4::class)
@LargeTest
class DefaultLocalDataSourceTest{

    private lateinit var weatherDatabase:WeatherDatabase
    private lateinit var defaultDataSource:DefaultLocalDataSource

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Before
    fun setUp(){

        val context = ApplicationProvider.getApplicationContext<Context>()
                weatherDatabase =
            Room.databaseBuilder(context, WeatherDatabase::class.java, BuildConfig.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()

        //initialize the Dao
        val weatherDao: WeatherDao = weatherDatabase.weatherDao()

        //instantiate the data source
        defaultDataSource = DefaultLocalDataSource(weatherDao)


    }



    @ExperimentalCoroutinesApi
    @Test
    fun `the right entity data stores successfully`()= runBlockingTest{

        //insert into database give a localDataSourceInstance
        defaultDataSource.saveWeatherReports(getFakeEntityData())

        //When the Db data is retrieved
        val result:List<WeatherEntity> = defaultDataSource.getWeatherReports().first()

        //Assert that the country is kenya
        MatcherAssert.assertThat( result[0].country, CoreMatchers.equalTo(KENYA))


    }

/*
    @Test
    fun `delete database successfully`(){


    }*/



    @After
    fun tearDown(){
                weatherDatabase.close()
    }


    fun getFakeEntityData(): WeatherEntity {

        return WeatherEntity(
            id = 1,
            temp = 1.4,
            humidity = 10,
            weatherDescription = "Foggy",
            weatherId = 12,
            weatherIcon = "12n",
            windDeg = 45.7,
            country = "Ke",
            city = "Kenya",
            windSpeed = 6.9,
            feelsLike = 56.8,
            minTemperature = 54.6,
            maxTemperature = 56.4,
            pressure = 5.8
        )
    }
}