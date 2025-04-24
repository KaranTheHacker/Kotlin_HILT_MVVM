package com.example.myapplication.di

import com.example.myapplication.services.GreetingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.util.Log

@Module
@InstallIn(SingletonComponent::class) // Install in singletonComponent
object AppModule {
    @Provides
    @Singleton //scope of the provided instance
    fun provideGreetingService():GreetingService{
        Log.d("AppModule","provideGreetingService() called")
        return GreetingService()
    }
}