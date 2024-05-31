package com.user.identificationapp.domain.di

import com.user.identificationapp.domain.repo.UserRepository
import com.user.identificationapp.domain.use_case.AddUserUseCase
import com.user.identificationapp.domain.use_case.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDomainModule {

    @Provides
    @Singleton
    fun provideGetUserUseCase(userRepository: UserRepository)= GetUserUseCase(userRepository)

    @Provides
    @Singleton
    fun provideAddUserUseCase(userRepository: UserRepository)= AddUserUseCase(userRepository)
}