package com.dylanmuszel.cleanarchitectureexample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.None
import com.dylanmuszel.cleanarchitectureexample.framework.login.UserNetworkSource
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.usecases.login.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private var _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> get() = _loginState

    fun onLoginButtonClicked(email: String, username: String) = viewModelScope.launch {
        loginUseCase(email, username).fold(::handleLoginFailure, ::handleLoginSuccess)
    }

    private fun handleLoginSuccess(none: None) {
        _loginState.value = LoginState.Success
    }

    private fun handleLoginFailure(failure: Failure) {
        _loginState.value = when (failure) {
            Failure.ServerError -> LoginState.ServerError
            is UserNetworkSource.InvalidUser -> LoginState.InvalidUser
            is LoginUseCase.EmptyField -> LoginState.EmptyField(failure.isEmailEmpty, failure.isUsernameEmpty)
            else -> throw IllegalStateException()
        }
    }
}
