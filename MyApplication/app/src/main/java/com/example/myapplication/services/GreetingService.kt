package com.example.myapplication.services

import android.util.Log
import javax.inject.Inject

class GreetingService @Inject constructor(){
    fun getGreeting(name:String):String{
        Log.d("GreetingService","getGreeting() called with name $name")
        return "Hello, $name!"
    }
}