package com.dylanmuszel.cleanarchitectureexample.presentation.posts

import com.dylanmuszel.domain.UserEntity

interface PostsView {

    fun showUser(user: UserEntity)

    fun goToLogin()
}
