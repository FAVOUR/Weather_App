package com.example.weatherapp.di.module

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.source.remote.service.WeatherService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule{


    @Provides
    @Singleton
    fun providePlainOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .apply {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addNetworkInterceptor(logging)

            }
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherServices(retrofit: Retrofit):WeatherService{
        return retrofit.create(WeatherService::class.java)
    }


}