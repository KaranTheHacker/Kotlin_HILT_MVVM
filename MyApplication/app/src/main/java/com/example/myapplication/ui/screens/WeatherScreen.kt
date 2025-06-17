package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.data.remote.model.Condition
import com.example.myapplication.data.remote.model.Day
import com.example.myapplication.data.remote.model.ForecastDay
import com.example.myapplication.ui.viewmodel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    forecast: List<ForecastDay>? = null,
    viewModel: WeatherViewModel? = null
) {
    // Use real ViewModel only if preview didn’t provide data
    val forecastState by viewModel?.forecast?.collectAsState() ?: remember { mutableStateOf(emptyList<ForecastDay>()) }
    val displayForecast = forecast ?: forecastState

    // Only fetch from network if real ViewModel is present
    LaunchedEffect(viewModel) {
        if (forecast == null && viewModel != null) {
            viewModel.fetchForecast("London")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("7-Day Weather Forecast") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(displayForecast) { day ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = day.date, fontWeight = FontWeight.Bold)
                            Text("Max: ${day.day.maxTempC}°C, Min: ${day.day.minTempC}°C")
                            Text("Condition: ${day.day.condition.text}")
                        }
                        Image(
                            painter = rememberAsyncImagePainter("https:${day.day.condition.icon}"),
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    val mockData = listOf(
        ForecastDay(
            date = "2025-06-12",
            day = Day(
                maxTempC = 32.5f,
                minTempC = 22.3f,
                condition = Condition(
                    text = "Sunny",
                    icon = "//cdn.weatherapi.com/weather/64x64/day/113.png"
                )
            )
        ),
        ForecastDay(
            date = "2025-06-18",
            day = Day(
                maxTempC = 25.0f,
                minTempC = 13.8f,
                condition = Condition(
                    text = "Partly Cloudy",
                    icon = "//cdn.weatherapi.com/weather/64x64/day/116.png"
                )
            )
        ),
        ForecastDay(
            date = "2025-06-19",
            day = Day(
                maxTempC = 22.5f,
                minTempC = 12.0f,
                condition = Condition(
                    text = "Rainy",
                    icon = "//cdn.weatherapi.com/weather/64x64/day/296.png"
                )
            )
        )
    )
    WeatherScreen(forecast = mockData) // ✅ No ViewModel passed
}
