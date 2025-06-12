package com.example.myapplication
//Application class
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import android.util.Log

@HiltAndroidApp
class MyApplication:Application()