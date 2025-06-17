package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.model.ForecastDay
import com.example.myapplication.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _forecast = MutableStateFlow<List<ForecastDay>>(emptyList())
    val forecast: StateFlow<List<ForecastDay>> = _forecast

    fun fetchForecast(location: String) {
        viewModelScope.launch {
            try {
                val response = repository.getWeatherForecast(location)
                _forecast.value = response.forecast.forecastDay
            } catch (e: Exception) {
                // Log or show error
            }
        }
    }
}
