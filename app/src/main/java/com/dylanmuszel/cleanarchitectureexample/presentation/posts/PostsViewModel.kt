package com.dylanmuszel.cleanarchitectureexample.presentation.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.None
import com.dylanmuszel.cleanarchitectureexample.framework.login.User
import com.dylanmuszel.cleanarchitectureexample.framework.login.toUser
import com.dylanmuszel.domain.UserEntity
import com.dylanmuszel.usecases.login.GetLoggedUserUseCase
import com.dylanmuszel.usecases.login.LogoutUseCase
import kotlinx.coroutines.launch

class PostsViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val getLoggedUserUseCase: GetLoggedUserUseCase
) : ViewModel() {

    private var _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    init {
        viewModelScope.launch {
            getLoggedUserUseCase().fold({}, ::handleLoggedUser)
        }
    }

    fun onLogoutClicked() = viewModelScope.launch {
        logoutUseCase().fold({}, ::handleLogout)
    }

    private fun handleLoggedUser(user: UserEntity) {
        _user.value = user.toUser()
    }

    private fun handleLogout(none: None) {
        _user.value = null
    }
}
