package com.dylanmuszel.cleanarchitectureexample.presentation.login

import arrow.core.None
import com.dylanmuszel.cleanarchitectureexample.framework.login.UserNetworkSource
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BasePresenter
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.usecases.login.LoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val loginUseCase: LoginUseCase) : BasePresenter<LoginView>() {

    fun onLoginButtonClicked(email: String, username: String) = launch {
        loginUseCase(email, username).fold(::handleLoginFailure, ::handleLoginSuccess)
    }

    private fun handleLoginSuccess(none: None) = view?.goToPosts()

    private fun handleLoginFailure(failure: Failure) {
        when (failure) {
            Failure.ServerError -> view?.showServerError()
            Failure.NetworkConnection -> view?.showNetworkConnectionError()
            is UserNetworkSource.InvalidUser -> view?.showInvalidUserError()
            is LoginUseCase.EmptyField -> {
                if (failure.isEmailEmpty) view?.showEmptyEmailError()
                if (failure.isUsernameEmpty) view?.showEmptyUsernameError()
            }
        }
    }
}
