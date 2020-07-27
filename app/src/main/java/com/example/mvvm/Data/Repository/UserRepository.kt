package com.example.mvvm.Data.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mvvm.Data.DAO.UserDao
import com.example.mvvm.Data.Model.User
import kotlin.random.Random

class UserRepository private constructor(private val userDao: UserDao) {
    private val userAccountLiveData: LiveData<User>? = null

    fun isValidAccount(email: String, password: String): Boolean {
        val userAccount: String = userDao.getPassword(email)
        if (userAccount == password) {
            return true
        }
        return false
    }


    fun insertUser(email: String, username: String, password: String) {
        userDao.insertUser(email, username, password)
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository {
            if (instance == null) {
                instance = UserRepository(userDao)
            }
            return instance!!
        }
    }
}