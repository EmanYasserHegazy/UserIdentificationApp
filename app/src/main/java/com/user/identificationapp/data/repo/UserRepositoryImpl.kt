package com.user.identificationapp.data.repo

import com.user.identificationapp.data.data_source.UserDao
import com.user.identificationapp.domain.model.User
import com.user.identificationapp.domain.model.toUserEntity
import com.user.identificationapp.domain.model.toUserList
import com.user.identificationapp.domain.repo.UserRepository

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun addUser(user: User) =
        userDao.insertUser(user.toUserEntity())


    override suspend fun getUser() =
        userDao.getUsers().toUserList()

}


