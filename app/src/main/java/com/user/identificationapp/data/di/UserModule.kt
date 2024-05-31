package com.user.identificationapp.data.di

import android.app.Application
import androidx.room.Room
import com.user.identificationapp.data.data_source.UserDatabase
import com.user.identificationapp.data.repo.UserRepositoryImpl
import com.user.identificationapp.domain.repo.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {


    @Provides
    @Singleton
    fun provideUserRepository(
        db: UserDatabase
    ): UserRepository {
        return UserRepositoryImpl(
            db.userDao
        )
    }

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app, UserDatabase::class.java, "user_db"
        ).build()
    }
}