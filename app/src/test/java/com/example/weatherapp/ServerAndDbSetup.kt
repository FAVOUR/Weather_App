package com.example.weatherapp

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import com.example.weatherapp.data.source.local.dao.WeatherDao
import com.example.weatherapp.data.source.local.db.WeatherDatabase
import com.example.weatherapp.data.source.local.entity.WeatherEntity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import java.io.IOException

open class ServerAndDbSetup {

//    "http://127.0.0.1:8080"

    val mockWebServer =MockWebServer()
    private lateinit var weatherDatabase: WeatherDatabase
    internal lateinit var weatherDao: WeatherDao
    val dummyEntityData:WeatherEntity =getWeatherEntity()

    @Before
    fun setup(){
        mockWebServer.start(8080)
        mockWebServer.dispatcher=MockDispatchers()

        val context = ApplicationProvider.getApplicationContext<Context>()
        weatherDatabase=  Room.databaseBuilder(context, WeatherDatabase::class.java, BuildConfig.DATABASE_NAME)
                               .allowMainThreadQueries()
                                .build()
        weatherDao=weatherDatabase.weatherDao()


        //Given data inserted into the database
        weatherDao.insertWeatherData(dummyEntityData)
    }

    @After
    @Throws(IOException::class)
    fun teardown (){
        mockWebServer.shutdown()
        weatherDatabase.close()
    }


    fun getWeatherEntity():WeatherEntity{

        return WeatherEntity(id = 1,
            temp = 1.4,
            humidity=10,
            weatherDescription ="Foggy",
            weatherId = 12,
            weatherIcon = "12n",
            windDeg = 45.7,
            country = "Ke",
            city = "Kenya",
            windSpeed = 6.9
            )
    }


}