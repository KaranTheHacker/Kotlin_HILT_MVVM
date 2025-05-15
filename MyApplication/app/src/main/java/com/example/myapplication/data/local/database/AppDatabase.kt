package com.example.myapplication.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.room.processor.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.MyApplication
import com.example.myapplication.data.local.dao.UserDao
import com.example.myapplication.data.local.entity.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [User::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase?=null

        fun getInstance(context: MyApplication): AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        //4 Migration
        val MIGRATION_1_2 = object : Migration(1 ,2){
            override fun migrate(database: SupportSQLiteDatabase){
                // SQL statement to add the new column
                database.execSQL("ALTER TABLE users ADD COLUMN email TEXT")
            }
        }
    }
}
