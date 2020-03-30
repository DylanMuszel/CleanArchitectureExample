package com.dylanmuszel.usecases.login

import com.dylanmuszel.core.fp.Either
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.core.fp.None
import com.dylanmuszel.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(email: String, username: String): Either<Failure, None> = withContext(Dispatchers.IO) {
        if (email.isBlank() || username.isBlank()) {
            Either.Left(EmptyField(email.isBlank(), username.isBlank()))
        } else {
            userRepository.login(email, username)
        }
    }

    class EmptyField(
        val isEmailEmpty: Boolean,
        val isUsernameEmpty: Boolean
    ) : Failure.FeatureFailure()
}