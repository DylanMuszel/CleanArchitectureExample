package com.dylanmuszel.cleanarchitectureexample.framework.login

import com.dylanmuszel.domain.UserEntity

fun User.toEntity() = UserEntity(name, username, email, phone, website)

fun UserEntity.toUser() = User(name, username, email, phone, website)
