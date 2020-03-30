package com.dylanmuszel.cleanarchitectureexample.framework.login

import arrow.core.Either
import com.dylanmuszel.cleanarchitectureexample.framework.core.network.ConnectivityInfo
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.data.UserSource
import com.dylanmuszel.domain.UserEntity
import javax.inject.Inject

class UserNetworkSource @Inject constructor(
    private val userService: UserService,
    private val connectivityInfo: ConnectivityInfo
) : UserSource {

    override suspend fun getUser(email: String, password: String): Either<Failure, UserEntity> {

        if (!connectivityInfo.isOnline) {
            return Either.Left(Failure.NetworkConnection)
        }

        return try {
            userService.getUser(email, password)
                .firstOrNull()
                ?.toEntity()
                ?.let { Either.Right(it) }
                ?: Either.Left(InvalidUser)
        } catch (e: Exception) {
            Either.Left(Failure.ServerError)
        }
    }

    object InvalidUser : Failure.FeatureFailure()
}