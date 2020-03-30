package com.dylanmuszel.usecases.login

import com.dylanmuszel.core.fp.Either
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.data.UserRepository
import com.dylanmuszel.domain.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLoggedUserUseCase @Inject constructor(private val userRepository: UserRepository){

    suspend operator fun invoke(): Either<Failure, UserEntity> = withContext(Dispatchers.IO) {
        userRepository.getLoggedUser()
    }
}