package com.example.myapplication.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.viewmodel.UserViewModel
import androidx.compose.runtime.getValue


@Composable
fun UserListScreen(viewModel: UserViewModel = hiltViewModel()) {
    val users by viewModel.allUsersLiveData.observeAsState(initial = emptyList())

}