package com.example.myapplication.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
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
        setContentView(R.layout.activity_main) // Make sure you have this layout

        // 3. Use the injected GreetingService
        val greeting = greetingService.getGreeting("Hilt User")
        Log.d("MainActivity", "Greeting: $greeting") // Output to Logcat

        // (Optional) Display the greeting in a TextView
        val greetingTextView = findViewById<TextView>(R.id.greetingTextView) //  findViewById
        if (greetingTextView != null) {
            greetingTextView.text = greeting;
        }


    }
}

