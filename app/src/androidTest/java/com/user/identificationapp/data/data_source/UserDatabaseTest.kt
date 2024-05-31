package com.user.identificationapp.data.data_source

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.user.identificationapp.domain.model.Gender
import com.user.identificationapp.domain.model.User
import com.user.identificationapp.domain.model.toUserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    private lateinit var userDatabase: UserDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        userDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).allowMainThreadQueries().build()
        userDao = userDatabase.userDao
    }


    @Test
    fun testUserInsertAndRetrieve() = runBlocking {
        val user1 = User(
            "name", 24, Gender.MALE, "SW engineering"
        )

        userDao.insertUser(user1.toUserEntity())
        userDao.insertUser(user1.toUserEntity())
        userDao.insertUser(user1.toUserEntity())

        val userList = userDao.getUsers()


        assertThat(userList[0].name).contains(user1.toUserEntity().name)
    }


    @After
    fun tearDown() {
        userDatabase.close()
    }
}

