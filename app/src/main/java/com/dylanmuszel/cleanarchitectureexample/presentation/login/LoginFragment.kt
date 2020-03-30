package com.dylanmuszel.cleanarchitectureexample.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dylanmuszel.cleanarchitectureexample.R
import com.dylanmuszel.cleanarchitectureexample.databinding.FragmentLoginBinding
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BaseFragment
import com.dylanmuszel.cleanarchitectureexample.presentation.posts.PostsActivity

class LoginFragment private constructor() : BaseFragment<FragmentLoginBinding, LoginPresenter>(), LoginView {

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginButton.setOnClickListener {
            presenter.onLoginButtonClicked(binding.emailInput.text.toString(), binding.usernameInput.text.toString())
        }
    }

    override fun goToPosts() = startActivity(PostsActivity.getStartIntent(requireContext()).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    })

    override fun showServerError() =Toast.makeText(context, R.string.invalid_user_error, Toast.LENGTH_SHORT).show()

    override fun showInvalidUserError()= Toast.makeText(context, R.string.invalid_user_error, Toast.LENGTH_SHORT).show()

    override fun showEmptyEmailError() {
        binding.emailInput.error = getString(R.string.empty_email_error)
    }

    override fun showEmptyUsernameError() {
        binding.usernameInput.error = getString(R.string.empty_username_error)
    }

    companion object {

        fun newInstance() = LoginFragment()
    }
}