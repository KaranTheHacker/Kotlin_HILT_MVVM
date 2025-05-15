package com.example.myapplication

import androidx.room.Room
import androidx.room.processor.Context
import com.example.myapplication.data.local.dao.UserDao
import com.example.myapplication.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).addMigration(AppDatabase.MIGRATION_1_2).build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao =db.userDao()

//    fun provideSingletonCounter(): ScopedCounter {
////        println(myService.doSomething())
//        return ScopedCounter()
//    }

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