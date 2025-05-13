package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MyApplication
import com.example.myapplication.data.local.dao.UserDao
import com.example.myapplication.data.local.database.AppDatabase
import com.example.myapplication.data.local.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: MyApplication): AndroidViewModel(application) {

    private val userDao: UserDao
    val allUsersLiveData: LiveData<List<User>>
    private val database: AppDatabase

    //initialData ->livedata
    init {
        database = AppDatabase.getInstance(application)
        userDao = database.userDao()
        allUsersLiveData =userDao.getAllUsersLiveData()
    }

    private fun insertInitialData(){
        viewModelScope.launch(Dispatchers.IO) {
            if(userDao.getAllUsersLiveData().value?.isEmpty() != false){
                userDao.insertUser(User(firstName = "Karan", lastName = "Saxena", email = "saxenakaran1239@gmail.com"))
                userDao.insertUser((User))
            }
        }
    }

    fun addUser(firstName:String, lastName:String, email:String){
        viewModelScope..
    }


    //add user
    //clear user

}