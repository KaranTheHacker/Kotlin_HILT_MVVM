package com.example.myapplication.di

import androidx.room.Room
import com.example.myapplication.MyApplication
import com.example.myapplication.data.local.dao.UserDao
import com.example.myapplication.data.local.database.AppDatabase
import com.example.myapplication.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun provideAppDatabase(@ApplicationContext context: MyApplication): AppDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .addMigrations(AppDatabase.MIGRATION_1_2)
            .build()
    }

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository{
        return UserRepository(userDao, apiService = TODO())
    }
}