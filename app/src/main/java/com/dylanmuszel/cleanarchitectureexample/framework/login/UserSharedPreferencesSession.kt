package com.dylanmuszel.cleanarchitectureexample.framework.login

import arrow.core.Either
import com.dylanmuszel.cleanarchitectureexample.framework.core.sharedpreferences.SharedPreferencesManager
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.data.UserSession
import com.dylanmuszel.domain.UserEntity
import javax.inject.Inject

class UserSharedPreferencesSession @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : UserSession {

    override suspend fun getLoggedUser() = sharedPreferencesManager
        .get<User?>(USER_SESSION_KEY)
        ?.let { Either.Right(it.toEntity()) }
        ?: Either.Left(NoLoggedUser)

    override suspend fun setLoggedUser(user: UserEntity?) =
        sharedPreferencesManager.put(USER_SESSION_KEY, user?.toUser())

    object NoLoggedUser : Failure.FeatureFailure()

    companion object {

        private const val USER_SESSION_KEY = "USER_SESSION_KEY"
    }
}