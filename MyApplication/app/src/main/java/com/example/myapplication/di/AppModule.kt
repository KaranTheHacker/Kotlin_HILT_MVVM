package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.MyApplication
import com.example.myapplication.data.local.dao.UserDao
import com.example.myapplication.data.local.database.AppDatabase
import com.example.myapplication.data.remote.api.ApiService
import com.example.myapplication.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.junit.runner.manipulation.Ordering
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Install in singletonComponent
object AppModule {
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao{
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .addMigrations(AppDatabase.MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/") // example
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideUserRepository(userDao: UserDao,apiService: ApiService): UserRepository{
        return UserRepository(userDao, apiService)
    }
}