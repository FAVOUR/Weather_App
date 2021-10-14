package com.example.weatherapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.mappers.DataSourceMappers.toDataBaseModel
import com.example.weatherapp.data.mappers.DataSourceMappers.toUiModel
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.data.source.remote.helper.ResponseFromServer
import com.example.weatherapp.ui.model.WeatherData
import com.example.weatherapp.ui.util.Event
import com.google.gson.Gson
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {


    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<List<WeatherData>?>(null)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<List<WeatherData>?> = _uiState


    /**
     * Request a snackbar to display a string.
     *
     * This variable is private because we don't want to expose [MutableLiveData].
     *
     * MutableLiveData allows anyone to set a value, and [WeatherViewModel] is the only
     * class that should be setting values.
     */
    private val _snackbarMessage = MutableLiveData<Event<String?>>()

    /**
     * Request a snackbar to display a string.
     */
    val snackbarMessage: LiveData<Event<String?>> //You can use the event class
        get() = _snackbarMessage

    private val _displaySpinner = MutableLiveData<Boolean>(false)

    /**
     * Show a loading spinner if true
     */
    val displaySpinner: LiveData<Boolean>
        get() = _displaySpinner


    init {

        viewModelScope.launch {
            weatherRepository.getCurrentWeather()
                .map {
                    Log.e("Data from DB", Gson().toJson(it))
                    it.map { weatherEntity -> weatherEntity.toUiModel() }
                }
                .catch { throwable -> _snackbarMessage.value = Event(throwable.message) }

                .collect {
                    _uiState.value = it

                }

        }


    }


    fun updateWeatherData(cities: List<String>) {

        viewModelScope.launch {

            _displaySpinner.value = true

          val listOfData=  weatherRepository.fetchWeatherData(listOfCities = cities)
                .onEach {
                    _displaySpinner.value = false
                }
                .catch { throwable ->
                    throwable.printStackTrace()
                    _snackbarMessage.value = Event(throwable.message)
                }.toList()

                   weatherRepository.clearWeatherData()

              listOfData.forEach{ response ->

                    when (response) {
                        is ResponseFromServer.Success -> {
                            Log.e("Query successful", Gson().toJson(response.data))
                            weatherRepository.insertWeatherData(response.data.toDataBaseModel())
                        }
                        is ResponseFromServer.Error -> {
//
                            _snackbarMessage.value = Event("Could Not Retrieve some countries")
                        }
                    }

                }
        }


    }


}