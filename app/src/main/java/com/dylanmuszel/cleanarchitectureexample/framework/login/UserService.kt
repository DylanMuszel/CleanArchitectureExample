package com.dylanmuszel.cleanarchitectureexample.framework.login

import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("/users")
    suspend fun getUser(
        @Query("email") email: String,
        @Query("username") username: String
    ): List<User>
}