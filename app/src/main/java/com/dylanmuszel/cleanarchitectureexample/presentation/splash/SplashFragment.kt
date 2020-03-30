package com.dylanmuszel.cleanarchitectureexample.presentation.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dylanmuszel.cleanarchitectureexample.databinding.FragmentSplashBinding
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BaseFragment

class SplashFragment private constructor() : BaseFragment<FragmentSplashBinding>() {

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding = FragmentSplashBinding::inflate

    companion object {

        fun newInstance() = SplashFragment()
    }
}