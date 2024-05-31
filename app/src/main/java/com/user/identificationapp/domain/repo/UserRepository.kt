package com.user.identificationapp.domain.repo

import com.user.identificationapp.domain.model.User

interface UserRepository {
    suspend fun addUser(user: User)
    suspend fun getUser(): List<User>
}