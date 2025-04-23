package com.example.myapplication

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module // it signifies that this object contains methods that provide dependencies for the application
@InstallIn(SingletonComponent::class) // created once and live as long as application runs
object AppModule {
//    @Inject
//    lateinit var myService: MyService
    @Provides //transient or unscoped
    @Singleton
    fun provideSingletonCounter(): ScopedCounter {
//        println(myService.doSomething())
        return ScopedCounter()
    }

//    @Singleton
//    @Provides
//    fun providAppConfig():AppConfig{
//      return AppConfig()
//    }
}
//
//class AppConfig{
//    // .. blah
//}