package com.example.weatherapp.di.module

import com.example.weatherapp.MockDispatchers
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModuleTest {


    @Singleton
    @Provides
    @Named("test_database")
    fun provideWeatherDatabase(client: OkHttpClient): Retrofit {
        val mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        mockWebServer.dispatcher = MockDispatchers()
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}