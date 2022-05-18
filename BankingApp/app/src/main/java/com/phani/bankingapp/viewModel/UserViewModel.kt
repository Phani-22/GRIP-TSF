package com.phani.bankingapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.phani.bankingapp.data.User
import com.phani.bankingapp.database.UserDatabase
import com.phani.bankingapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    // viewModel class

    val allUsers: LiveData<List<User>>
    private val userRepository: UserRepository

    init {
        val userDao = UserDatabase.getUserDatabaseInstance(application).getUserDao()
        userRepository = UserRepository(userDao)
        allUsers = userRepository.allUsers
    }

    fun transferMoney(u1: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.transferMoney(u1)
        }
    }
}