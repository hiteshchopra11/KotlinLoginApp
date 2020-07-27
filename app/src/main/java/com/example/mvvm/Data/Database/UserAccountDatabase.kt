package com.example.mvvm.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm.Data.DAO.UserDao
import com.example.mvvm.Data.Model.User

@Database(entities = [User::class], version = 1)
abstract class UserAccountDatabase : RoomDatabase() {

    abstract fun userAccountDao(): UserDao

    companion object {
        private var INSTANCE: UserAccountDatabase? = null

        fun getAppDatabase(context: Context): UserAccountDatabase {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserAccountDatabase::class.java,
                    "user-database"
                ).allowMainThreadQueries().build()

            }

            return INSTANCE!!

        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}