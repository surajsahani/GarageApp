package com.martial.garageapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @Author: surajsahani
 * @Date: 18/08/22
 */

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Query("Select * from user_table WHERE username_user =:username")
    suspend fun getUsername(username: String?): UserEntity
}