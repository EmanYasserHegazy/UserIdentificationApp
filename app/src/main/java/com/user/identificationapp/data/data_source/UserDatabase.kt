package com.user.identificationapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1
)

abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}