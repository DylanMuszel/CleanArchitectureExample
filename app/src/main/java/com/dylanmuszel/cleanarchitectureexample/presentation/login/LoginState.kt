package com.dylanmuszel.cleanarchitectureexample.presentation.login

sealed class LoginState {
    class EmptyField(val isEmailEmpty: Boolean, val isUsernameEmpty: Boolean) : LoginState()
    object InvalidUser : LoginState()
    object ServerError : LoginState()
    object Success : LoginState()
}