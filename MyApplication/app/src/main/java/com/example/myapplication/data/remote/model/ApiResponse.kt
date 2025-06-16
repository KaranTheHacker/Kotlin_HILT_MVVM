package com.example.myapplication.data.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    val forecast: Forecast
)

data class Forecast(
    @SerializedName("forecastday")
    val forecastDay:List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day
)

data class Day(
    @SerializedName("maxtemp_c")
    val maxTempC:Float,
    @SerializedName("mintemp_c")
    val minTempC:Float,
    val condition:Condition
)

data class Condition(
    val text: String,
    val icon: String
)