package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.local.dao.UserDao
import com.example.myapplication.data.local.entity.User
import com.example.myapplication.data.remote.api.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val apiService: ApiService
) {
    val allUsersFlow: Flow<List<User>> = userDao.getAllUserFlow()
    val allUsersLiveData: LiveData<List<User>> = userDao.getAllUsersLiveData()

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteALL()
    }

//    fun getUser(id:Int): Flow<User>{
//        return if(isOnline()){
//            flow{emit(apiService.getUser(id))}
//        } else{
//            userDao.getUserById(id)
//        }
//    }
}