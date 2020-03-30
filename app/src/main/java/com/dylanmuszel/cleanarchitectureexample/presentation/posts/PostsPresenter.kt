package com.dylanmuszel.cleanarchitectureexample.presentation.posts

import arrow.core.None
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BasePresenter
import com.dylanmuszel.domain.UserEntity
import com.dylanmuszel.usecases.login.GetLoggedUserUseCase
import com.dylanmuszel.usecases.login.LogoutUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsPresenter @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val getLoggedUserUseCase: GetLoggedUserUseCase
) : BasePresenter<PostsView>() {

    init {
        launch {
            getLoggedUserUseCase().fold({}, ::handleLoggedUser)
        }
    }

    fun onLogoutClicked() = launch {
        logoutUseCase().fold({}, ::handleLogout)
    }

    private fun handleLoggedUser(user: UserEntity) = view?.showUser(user)

    private fun handleLogout(none: None) = view?.goToLogin()
}
