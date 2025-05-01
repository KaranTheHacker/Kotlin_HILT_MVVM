package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.entity.User

@Dao
interface UserDao{

    @Query("SELECT * FROM users")
    fun getAll():List<User>

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray):List<User>

    @Query("SELECT TOP 1 * FROM users WHERE first_name LIKE :first AND last_name LIKE :last")  //ORDER BY uid DESC LIMIT 1
    fun findByName(first:String, last:String):User

    @Insert
    fun insertAll(vararg users:User)

    @Delete
    fun delete(vararg user: User)

    @Update
    fun update(vararg user:User)

}