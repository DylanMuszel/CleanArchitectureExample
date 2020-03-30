package com.dylanmuszel.usecases.login

import com.dylanmuszel.core.fp.Either
import com.dylanmuszel.core.fp.None
import com.dylanmuszel.data.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val userSession: UserSession) {

    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        userSession.setLoggedUser(null)
        Either.Right(None)
    }
}