package com.dylanmuszel.cleanarchitectureexample.presentation.login

interface LoginView {

    fun goToPosts()

    fun showServerError()

    fun showNetworkConnectionError()

    fun showInvalidUserError()

    fun showEmptyEmailError()

    fun showEmptyUsernameError()
}
