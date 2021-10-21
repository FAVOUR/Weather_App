package com.example.weatherapp

import com.example.weatherapp.data.source.local.entity.WeatherEntity
import com.example.weatherapp.data.source.remote.service.WeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

open class ServerAndDbSetup {

//    "http://127.0.0.1:8080"


//    private lateinit var weatherDatabase: WeatherDatabase
//    internal lateinit var weatherDao: WeatherDao
//    val dummyEntityData: WeatherEntity = getWeatherEntity()
val mockWebServer = MockWebServer()

    lateinit var weatherService:WeatherService
    @Before
    fun setup() {

        mockWebServer.dispatcher = MockDispatchers()


           val  loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
           val  okHttpClient = buildOkhttpClient(loggingInterceptor)

           val retrofit:Retrofit=   Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

               weatherService = retrofit.create(WeatherService::class.java)

//        mockWebServer.start()

//        val context = ApplicationProvider.getApplicationContext<Context>()
//        weatherDatabase =
//            Room.databaseBuilder(context, WeatherDatabase::class.java, BuildConfig.DATABASE_NAME)
//                .allowMainThreadQueries()
//                .build()
//        weatherDao = weatherDatabase.weatherDao()


        //Given data inserted into the database
//        weatherDao.insertWeatherData(dummyEntityData)
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        mockWebServer.shutdown()
//        weatherDatabase.close()
    }


    fun getWeatherEntity(): WeatherEntity {

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

    private fun buildOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }


}