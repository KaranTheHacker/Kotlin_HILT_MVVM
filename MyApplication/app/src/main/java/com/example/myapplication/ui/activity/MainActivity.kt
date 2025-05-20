package com.example.myapplication.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.viewmodel.UserViewModel
import com.example.myapplication.ui.viewmodel.UserViewModelSF
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity() { //an activity represent a single screen with a user interface

    private lateinit var userViewModel: UserViewModel
//    private lateinit var userViewModel: UserViewModelSF
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) { //lifecycle method
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBindin
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Make sure you have this layout
        val textView = binding.textView
        val userListTextView = binding.userListTextView
        val addUserButton = binding.addUserButton
        val clearButton = binding.clearButton

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        //Observe LiveModel
        userViewModel.allUsersLiveData.observe(this){
            users ->
            val userList = users.joinToString("\n"){
                "${it.firstName} ${it.lastName}:- ${(it.email ?: "No Email" )}"
            }
            binding.userListTextView.text = buildString {
                append("Users: \n ")
                append(userList)
            }
        }
//
//        //Observer Stateflow
//        lifecycleScope.launch {
//            userViewModel.allUsersStateFlow.collect { users ->
//                val userList = users.joinToString("\n"){"${it.firstName} ${it.lastName} ${(it.email ?: "No Email" )}"}
//                userListTextView.text = buildString {
//                                                    append("Users: \n ")
//                                                    append(userList)
//                                                }
//            }
//        }

        //Observe LiveData
//        userViewModel.allUsersLiveData.observe(this) {
//                users ->
//                val userList = users.joinToString("\n"){"${it.firstName} ${it.lastName} ${(it.email ?: "No Email" )}"}
//                userListTextView.text ="Users: \n $userList"
//        }
        addUserButton.setOnClickListener{
            val firstName = "New" + Random.nextInt(100)
            val lastName = "User" + Random.nextInt(100)
            val email = "user${Random.nextInt(100)}@happy.com"
            userViewModel.addUser(firstName,lastName,email)
        }
        clearButton.setOnClickListener{
            userViewModel.clearUsers()
        }
    }


}

