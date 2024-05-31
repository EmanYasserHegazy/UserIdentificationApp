package com.user.identificationapp.domain.use_case

import com.user.identificationapp.domain.model.User
import com.user.identificationapp.domain.repo.UserRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(user: User) = userRepository.addUser(user)


}