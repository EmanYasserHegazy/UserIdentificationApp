package com.user.identificationapp.domain.model

import android.os.Parcelable
import com.user.identificationapp.data.data_source.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val name: String, val age: Int, val gender: Gender, val userPosition: String) :
    Parcelable

enum class Gender {
    FEMALE, MALE
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        name = this.name,
        age = this.age,
        gender = this.gender.name,
        userJobTitle = this.userPosition
    )
}

fun UserEntity.toUser(): User {
    return User(
        name = this.name,
        age = this.age ?: 0,
        gender = Gender.valueOf(this.gender),
        userPosition = this.userJobTitle ?: ""
    )
}

fun List<UserEntity>.toUserList(): List<User> {
    return map { it.toUser() }
}