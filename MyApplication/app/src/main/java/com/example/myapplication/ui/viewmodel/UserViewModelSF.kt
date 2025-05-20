package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.dao.UserDao
import com.example.myapplication.data.local.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModelSF @Inject constructor(
    private val userDao: UserDao
): ViewModel() {
    val allUsersStateFlow: StateFlow<List<User>>
    private val _allUserStateFlow = MutableStateFlow<List<User>>(emptyList())

    //initialData ->livedata
    init {
        allUsersStateFlow =_allUserStateFlow.asStateFlow()
        observeUser()
        insertInitialData()
    }

    private fun observeUser(){
        viewModelScope.launch {
            userDao.getAllUserFlow().collect { _allUserStateFlow.value = it }
        }
    }

    private fun insertInitialData(){
        viewModelScope.launch(Dispatchers.IO) {
            //true != false  -> true
            if(userDao.getAllUserFlow().first().isEmpty()){
                userDao.insertUser(User(firstName = "Karan", lastName = "Saxena", email = "saxenakaran1239@gmail.com"))
                userDao.insertUser(User(firstName = "Vijay", lastName = "Varma", email = "@gmail.com"))
            }
        }
    }

    fun addUser(firstName:String, lastName:String, email:String){
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUser(User(firstName = firstName, lastName = lastName, email = email))
        }
    }

    fun clearUser(firstName:String, lastName:String, email:String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.delete(User(firstName = firstName, lastName = lastName, email = email))
        }
    }

    fun clearUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.deleteALL()
        }
    }
}