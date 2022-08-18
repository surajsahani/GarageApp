package com.martial.garageapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: surajsahani
 * @Date: 18/08/22
 */
@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "username_user")
    var username: String?,
    @ColumnInfo(name = "password_user")
    val password : String?
)