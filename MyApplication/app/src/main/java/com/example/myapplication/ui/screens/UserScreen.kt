package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.viewmodel.UserViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun UserListScreen(viewModel: UserViewModel = hiltViewModel()) {
    //Compose way to observe LiveData
    val users by viewModel.allUsersLiveData.observeAsState(initial = emptyList())
    // val users= viewModel.allUsersLiveData
    //by- > auto-subscribe
    //by -? delegation
    //Horizontal Center line
//    Box(
//        Modifier
//            .fillMaxWidth()
//            .height(1.dp)
//            .background(Color.Red)
//    )
    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxSize()
            .padding(16.dp), // density pixel -> 1 dp = 1 pixel on 160 dpi screen(base)
                                // 320 dpi -> 1dp = 2actual -> 480 dpi -> 3
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text("Users:", style = MaterialTheme.typography.titleLarge)

        Column ( verticalArrangement = Arrangement.spacedBy(8.dp)){
            users.forEach {
                Text(text = "${it.firstName} ${it.lastName} (${it.email ?:"NO Email"})")
            }
        }

        Row (horizontalArrangement = Arrangement.spacedBy(8.dp)){
            Button( onClick = {
                val firstName = "New" + Random.nextInt(100)
                val lastName = "User" + Random.nextInt(100)
                val email = "user${Random.nextInt(100)}@happy.com"
                viewModel.addUser(firstName,lastName,email)
            }) {
                Text("Add User")
            }
            Button(
                onClick = {
                    viewModel.clearUsers()
                }
            ) {
                Text("Clear Users")
            }
        }

    }
}
