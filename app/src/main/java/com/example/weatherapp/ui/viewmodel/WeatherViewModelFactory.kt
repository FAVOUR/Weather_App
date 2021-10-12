package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class WeatherViewModelFactory(
    private val viewmodels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    private val TAG = "ViewModelProviderFactory"

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return viewmodels[modelClass]?.get() as T
    }


}