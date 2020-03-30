package com.dylanmuszel.cleanarchitectureexample.presentation.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dylanmuszel.cleanarchitectureexample.R
import com.dylanmuszel.cleanarchitectureexample.databinding.FragmentPostsBinding
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BaseFragment
import com.dylanmuszel.cleanarchitectureexample.presentation.login.LoginActivity
import com.dylanmuszel.domain.UserEntity

class PostsFragment private constructor() : BaseFragment<FragmentPostsBinding, PostsPresenter>(), PostsView {

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostsBinding = FragmentPostsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.logoutButton.setOnClickListener { presenter.onLogoutClicked() }
    }

    override fun showUser(user: UserEntity) {
        binding.title.text = getString(R.string.welcome, user.name)
    }

    override fun goToLogin() = startActivity(LoginActivity.getStartIntent(requireContext()))

    companion object {

        fun newInstance() = PostsFragment()
    }
}