package com.dylanmuszel.cleanarchitectureexample.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylanmuszel.core.fp.Failure
import com.dylanmuszel.domain.UserEntity
import com.dylanmuszel.usecases.login.GetLoggedUserUseCase
import kotlinx.coroutines.*

class SplashViewModel(private val getLoggedUserUseCase: GetLoggedUserUseCase) : ViewModel() {

    private var _sessionState = MutableLiveData<SessionState>()
    val sessionState: LiveData<SessionState> get() = _sessionState

    init {
        val loggedUser = viewModelScope.async { getLoggedUserUseCase() }
        viewModelScope.launch {
            withContext(Dispatchers.Default) { delay(SPLASH_DELAY) }
            loggedUser.await().fold(::handleSessionNotLogged, ::handleSessionLogged)
        }
    }

    private fun handleSessionNotLogged(failure: Failure) {
        _sessionState.value = SessionState.NOT_LOGGED
    }

    private fun handleSessionLogged(user: UserEntity) {
        _sessionState.value = SessionState.LOGGED
    }

    companion object {
        private const val SPLASH_DELAY = 2_000L
    }
}
