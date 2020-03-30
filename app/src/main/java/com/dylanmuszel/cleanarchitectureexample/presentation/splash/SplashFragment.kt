package com.dylanmuszel.cleanarchitectureexample.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.dylanmuszel.cleanarchitectureexample.databinding.FragmentSplashBinding
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BaseFragment
import com.dylanmuszel.cleanarchitectureexample.presentation.login.LoginActivity
import com.dylanmuszel.cleanarchitectureexample.presentation.posts.PostsActivity
import javax.inject.Inject

class SplashFragment private constructor() : BaseFragment<FragmentSplashBinding>() {

    @Inject
    lateinit var viewModel: SplashViewModel

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding =
        FragmentSplashBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.sessionState.observe(this, Observer(this::updateUI))
    }

    private fun updateUI(state: SessionState) {
        val intent = when (state) {
            SessionState.NOT_LOGGED -> LoginActivity.getStartIntent(requireContext())
            SessionState.LOGGED -> PostsActivity.getStartIntent(requireContext()).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
        startActivity(intent)
    }

    companion object {

        fun newInstance() = SplashFragment()
    }
}