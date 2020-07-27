package com.example.mvvm.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User
    (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    var name: String,
    var password: String
)
