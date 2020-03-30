package com.dylanmuszel.cleanarchitectureexample.presentation.posts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dylanmuszel.cleanarchitectureexample.R
import com.dylanmuszel.cleanarchitectureexample.databinding.FragmentLoginBinding
import com.dylanmuszel.cleanarchitectureexample.databinding.FragmentPostsBinding
import com.dylanmuszel.cleanarchitectureexample.framework.login.User
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BaseFragment
import com.dylanmuszel.cleanarchitectureexample.presentation.login.LoginActivity
import javax.inject.Inject

class PostsFragment private constructor() : BaseFragment<FragmentPostsBinding>() {

    @Inject
    lateinit var viewModel: PostsViewModel

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostsBinding =
        FragmentPostsBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.user.observe(this, Observer(::updateUI))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.logoutButton.setOnClickListener { viewModel.onLogoutClicked() }
    }

    private fun updateUI(user: User?) {
        if (user != null) {
            binding.title.text = getString(R.string.welcome, user.name)
        } else {
            startActivity(LoginActivity.getStartIntent(requireContext()))
        }
    }

    companion object {

        fun newInstance() = PostsFragment()
    }
}