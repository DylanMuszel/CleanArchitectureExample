package com.dylanmuszel.cleanarchitectureexample.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BasePresenter
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.domain.UserEntity
import com.dylanmuszel.usecases.login.GetLoggedUserUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

class SplashPresenter @Inject constructor(private val getLoggedUserUseCase: GetLoggedUserUseCase) : BasePresenter<SplashView>() {

    init {
        val loggedUser = async { getLoggedUserUseCase() }
        launch {
            withContext(Dispatchers.Default) { delay(SPLASH_DELAY) }
            loggedUser.await().fold(::handleSessionNotLogged, ::handleSessionLogged)
        }
    }

    private fun handleSessionNotLogged(failure: Failure) = view?.goToLogin()

    private fun handleSessionLogged(user: UserEntity) = view?.goToPosts()

    companion object {
        private const val SPLASH_DELAY = 2_000L
    }
}
