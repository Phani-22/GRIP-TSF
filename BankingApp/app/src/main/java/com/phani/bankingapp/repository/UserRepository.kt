package com.phani.bankingapp.repository

import com.phani.bankingapp.data.User
import com.phani.bankingapp.data.UserDao

class UserRepository(private val userDao: UserDao) {

    val allUsers = userDao.getAllUsers()

    suspend fun transferMoney(u1: User) {
        userDao.transferMoney(u1)
    }
}