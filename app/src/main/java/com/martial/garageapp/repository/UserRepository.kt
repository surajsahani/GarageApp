package com.martial.garageapp.repository

import android.content.Context
import android.os.AsyncTask
import com.martial.garageapp.database.UserDao
import com.martial.garageapp.database.UserDatabase
import com.martial.garageapp.database.UserEntity

/**
 * @Author: surajsahani
 * @Date: 18/08/22
 */
class UserRepository(context: Context) {

    var db: UserDao = UserDatabase.getInstance(context).userDao()

    fun insertUser(users: UserEntity) {
        insertAsyncTask(db).execute(users)
    }
    private class insertAsyncTask internal constructor(private val userDao: UserDao) :
        AsyncTask<UserEntity, Void, Void>() {
        override fun doInBackground(vararg params: UserEntity): Void? {
            userDao.insertUser(params[0])
            return null
        }
    }
}