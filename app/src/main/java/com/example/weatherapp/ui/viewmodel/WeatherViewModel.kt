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
import com.example.weatherapp.ui.util.Extensions.ABUJA
import com.example.weatherapp.ui.util.Extensions.AMAZON
import com.example.weatherapp.ui.util.Extensions.ANKARA
import com.example.weatherapp.ui.util.Extensions.BAGHDAD
import com.example.weatherapp.ui.util.Extensions.BELARUS
import com.example.weatherapp.ui.util.Extensions.CAIRO
import com.example.weatherapp.ui.util.Extensions.JAKATA
import com.example.weatherapp.ui.util.Extensions.KANO
import com.example.weatherapp.ui.util.Extensions.KENYA
import com.example.weatherapp.ui.util.Extensions.LAGOS
import com.example.weatherapp.ui.util.Extensions.LESOTHO
import com.example.weatherapp.ui.util.Extensions.NEW_YORK
import com.example.weatherapp.ui.util.Extensions.PERU
import com.example.weatherapp.ui.util.Extensions.TEXAS
import com.example.weatherapp.ui.util.Extensions.WESTHAM
import com.example.weatherapp.ui.util.Extensions.WINNIPEG
import com.google.gson.Gson
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val cities by lazy {
        arrayListOf(
            KENYA, CAIRO, LAGOS, ABUJA, NEW_YORK, TEXAS,
            AMAZON, BELARUS, LESOTHO, JAKATA, ANKARA,
            KANO, PERU, WINNIPEG, BAGHDAD, WESTHAM
        )
    }


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
    private val _snackBarMessage = MutableLiveData<Event<String?>>()

    /**
     * Request a snackbar to display a string.
     */
    val snackBarMessage: LiveData<Event<String?>> //You can use the event class
        get() = _snackBarMessage

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
//                    Log.e("Data from DB", Gson().toJson(it))
                    it.map { weatherEntity -> weatherEntity.toUiModel() }
                }
                .catch { throwable -> _snackBarMessage.value = Event(throwable.message) }

                .collect {
                    _uiState.value = it

                }

        }

        updateWeatherData()


    }


    fun updateWeatherData() {


        viewModelScope.launch {

            _displaySpinner.value = true

            val listOfData = weatherRepository.fetchWeatherData(listOfCities = cities)
                .catch { throwable ->
                    throwable.printStackTrace()
                    _snackBarMessage.value = Event(throwable.message)
                }.toList()

            _displaySpinner.value = false


            if (listOfData.isNotEmpty()) {

                weatherRepository.clearWeatherData()

                listOfData.forEach { response ->

                    when (response) {
                        is ResponseFromServer.Success -> {

                            weatherRepository.insertWeatherData(response.data.toDataBaseModel())
                        }
                        is ResponseFromServer.Error -> {

                            _snackBarMessage.value = Event("Could Not Retrieve some countries")
                        }
                    }

                }
            }
        }


    }


}