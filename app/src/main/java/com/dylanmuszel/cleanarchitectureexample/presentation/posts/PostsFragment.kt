package com.dylanmuszel.cleanarchitectureexample.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dylanmuszel.cleanarchitectureexample.databinding.FragmentPostsBinding
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BaseFragment

class PostsFragment private constructor() : BaseFragment<FragmentPostsBinding>() {

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostsBinding = FragmentPostsBinding::inflate

    companion object {

        fun newInstance() = PostsFragment()
    }
}