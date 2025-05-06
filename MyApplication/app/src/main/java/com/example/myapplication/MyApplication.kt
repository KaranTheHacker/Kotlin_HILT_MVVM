package com.example.myapplication
//Application class
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import android.util.Log

@HiltAndroidApp
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("MyApplication","Application class created")
        userDao.getALL().observe(this){

        }
    }
}