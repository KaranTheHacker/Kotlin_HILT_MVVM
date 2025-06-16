package com.example.myapplication.data.remote.api

import androidx.compose.ui.input.key.Key
import com.example.myapplication.data.remote.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int=7
    ): ForecastResponse

}