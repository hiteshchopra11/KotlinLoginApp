package com.example.mvvm.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.Data.Database.UserAccountDatabase
import com.example.mvvm.Data.Repository.UserRepository

class UserViewModel(context: Context) : ViewModel() {

    private val userRepository: UserRepository =
        UserRepository.getInstance(UserAccountDatabase.getAppDatabase(context).userAccountDao())

    internal fun createUser(email: String, username: String, password: String) {
        userRepository.insertUser(email, username, password)
    }

    internal fun checkValidLogin(email: String, password: String): Boolean {
        return userRepository.isValidAccount(email, password)
    }

    class Factory internal constructor(context: Context) : ViewModelProvider.Factory {
        private val context: Context = context.applicationContext

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(context) as T
        }
    }
}