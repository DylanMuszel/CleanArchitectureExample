package com.dylanmuszel.cleanarchitectureexample.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dylanmuszel.cleanarchitectureexample.R
import com.dylanmuszel.cleanarchitectureexample.databinding.FragmentLoginBinding
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BaseFragment
import com.dylanmuszel.cleanarchitectureexample.presentation.posts.PostsActivity
import javax.inject.Inject

class LoginFragment private constructor() : BaseFragment<FragmentLoginBinding>() {

    @Inject
    lateinit var viewModel: LoginViewModel

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding = FragmentLoginBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loginState.observe(this, Observer(this::updateUI))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginButton.setOnClickListener {
            viewModel.onLoginButtonClicked(binding.emailInput.text.toString(), binding.usernameInput.text.toString())
        }
    }

    private fun updateUI(state: LoginState) {
        when (state) {
            is LoginState.EmptyField -> {
                if (state.isEmailEmpty) binding.emailInput.error = getString(R.string.empty_email_error)
                if (state.isUsernameEmpty) binding.usernameInput.error = getString(R.string.empty_username_error)
            }
            LoginState.InvalidUser -> Toast.makeText(context, R.string.invalid_user_error, Toast.LENGTH_SHORT).show()
            LoginState.ServerError -> Toast.makeText(context, R.string.invalid_user_error, Toast.LENGTH_SHORT).show()
            LoginState.NetworkConnectionError ->
                Toast.makeText(context, R.string.network_connection_error, Toast.LENGTH_SHORT).show()
            LoginState.Success -> startActivity(PostsActivity.getStartIntent(requireContext()).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }

    companion object {

        fun newInstance() = LoginFragment()
    }
}