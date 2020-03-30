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

class SplashFragment private constructor() : BaseFragment<FragmentSplashBinding, SplashPresenter>(), SplashView {

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding = FragmentSplashBinding::inflate

    override fun goToLogin() = startActivity(LoginActivity.getStartIntent(requireContext()))

    override fun goToPosts() = startActivity(PostsActivity.getStartIntent(requireContext()).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    })

    companion object {

        fun newInstance() = SplashFragment()
    }
}