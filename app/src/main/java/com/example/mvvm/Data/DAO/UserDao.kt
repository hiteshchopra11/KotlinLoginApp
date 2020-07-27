package com.example.mvvm.Data.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvm.Data.Model.User
import java.net.PasswordAuthentication


@Dao
interface UserDao {
    @Query("INSERT INTO user(EMAIL,NAME,PASSWORD) VALUES (:email,:name,:password)")
    fun insertUser(email: String, name: String, password: String)


    @Query("SELECT password FROM user WHERE email LIKE:email")
    fun getPassword(email: String): String
}