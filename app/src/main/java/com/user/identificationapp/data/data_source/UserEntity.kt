package com.user.identificationapp.data.data_source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int?,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "user_job_title") val userJobTitle: String?
)
