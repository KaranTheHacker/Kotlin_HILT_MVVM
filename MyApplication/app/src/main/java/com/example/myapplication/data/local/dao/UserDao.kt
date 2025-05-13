package com.example.myapplication.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.local.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao{

    @Query("SELECT * FROM users ORDER BY uid DESC")
    fun getAllUserFlow():Flow<List<User>>

    @Query("SELECT * FROM users")
    fun getAllUsersLiveData(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray):List<User>

    @Insert
    suspend fun insertUser(user: User)

//    @Query(value = "SELECT TOP 1 * FROM users WHERE first_name LIKE :first AND last_name LIKE :last")  //ORDER BY uid DESC LIMIT 1
//    fun findByName(first:String, last:String):User

    @Insert
    fun insertAll(vararg users:User)

    @Delete
    fun delete(vararg user: User)

    @Update
    fun update(vararg user:User)

}