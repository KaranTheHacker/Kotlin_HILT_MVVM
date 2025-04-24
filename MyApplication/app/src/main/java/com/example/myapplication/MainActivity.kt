package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.services.GreetingService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() { //an activity represent a single screen with a user interface

    @Inject
    lateinit var greetingService: GreetingService

    override fun onCreate(savedInstanceState: Bundle?) { //lifecycle method
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        // layout

        val greeting = greetingService.getGreeting("Karan")
        Log.d("MainActivity","Greeting: $greeting")



    }
}

