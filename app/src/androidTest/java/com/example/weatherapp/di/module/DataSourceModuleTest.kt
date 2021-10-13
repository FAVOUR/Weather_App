//package com.example.weatherapp.di.module
//
//import com.example.weatherapp.data.source.local.DefaultLocalDataSource
//import com.example.weatherapp.data.source.local.LocalDataSource
//import com.example.weatherapp.data.source.remote.DefaultRemoteDataSource
//import com.example.weatherapp.data.source.remote.RemoteDataSource
//import dagger.Binds
//import org.junit.Assert.*
//
//import org.junit.Test
//import javax.inject.Singleton
//
//abstract class DataSourceModuleTest {
//
//    @Singleton
//    @Binds
//    abstract fun bindRemoteDataSourceModule(defaultRemoteDataSource: DefaultRemoteDataSource): RemoteDataSource
//
//    @Singleton
//    @Binds
//    abstract fun bindLocalDataSource(defaultLocalDataSource: DefaultLocalDataSource): LocalDataSource
//
//}