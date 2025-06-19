package com.example.myapplication.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.myapplication.ui.screens.SettingsScreen
import com.example.myapplication.ui.screens.UserListScreen
import com.example.myapplication.ui.screens.WeatherScreen
import com.example.myapplication.ui.viewmodel.WeatherViewModel


sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
    object User : Screen("user", Icons.Default.Person, "User")
    object Weather : Screen("weather", Icons.Default.Home, "Weather")
    object Settings : Screen("settings", Icons.Default.Settings, "Settings")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavGraph() {
    val navController = rememberNavController()
    val items = listOf(Screen.User, Screen.Weather, Screen.Settings)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination == screen.route,
                        onClick = { navController.navigate(screen.route) },
                        icon = { Icon(imageVector = screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.User.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.User.route) { UserListScreen() }
            composable(Screen.Weather.route) {
                val viewModel: WeatherViewModel = hiltViewModel()
                WeatherScreen(viewModel = viewModel)
            }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }
}