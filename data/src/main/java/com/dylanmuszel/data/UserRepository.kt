package com.dylanmuszel.data

import com.dylanmuszel.core.fp.*
import com.dylanmuszel.domain.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userSource: UserSource,
    private val userSession: UserSession
) {

    suspend fun login(email: String, password: String): Either<Failure, None> {
        userSource.getUser(email, password).fold(
            { return Either.Left(it) },
            { userSession.setLoggedUser(it) })

        return Either.Right(None)
    }

    suspend fun getLoggedUser(): Either<Failure, UserEntity> = userSession.getLoggedUser()
}

interface UserSource {

    suspend fun getUser(email: String, password: String): Either<Failure, UserEntity>
}

interface UserSession {

    suspend fun getLoggedUser(): Either<Failure, UserEntity>

    suspend fun setLoggedUser(user: UserEntity?)
}
