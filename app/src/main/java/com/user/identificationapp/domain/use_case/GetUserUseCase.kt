package com.user.identificationapp.domain.use_case

import com.user.identificationapp.domain.repo.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke() =
        userRepository.getUser()
}