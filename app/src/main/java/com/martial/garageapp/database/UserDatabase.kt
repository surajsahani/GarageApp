package com.martial.garageapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @Author: surajsahani
 * @Date: 18/08/22
 */
@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract  fun userDao() : UserDao

    companion object {
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context) : UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val data = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "Data.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = data
                data
            }
        }
    }
}