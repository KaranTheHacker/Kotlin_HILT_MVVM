package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.dao.UserDao
import com.example.myapplication.data.local.entity.User
import com.example.myapplication.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val allUsersLiveData: LiveData<List<User>> = repository.allUsersLiveData

    init{
        insertInitialData()
    }

    private fun insertInitialData(){
        viewModelScope.launch(Dispatchers.IO) {
            //true != false --> true
            if(repository.allUsersFlow.first().isEmpty() != false){
                repository.insertUser(User(firstName = "Karan", lastName = "Saxena", email = "saxenakaran1239@gmail.com"))
                repository.insertUser(User(firstName = "Vijay", lastName = "Varma", email = "@gmail.com"))
            }
        }
    }

    fun addUser(firstName:String, lastName:String, email:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(User(firstName = firstName, lastName = lastName, email = email))
        }
    }
//
//    fun clearUser(firstName:String, lastName:String, email:String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.delete(User(firstName = firstName, lastName = lastName, email = email))
//        }
//    }

    fun clearUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }
}