package com.dylanmuszel.cleanarchitectureexample.presentation.login

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dylanmuszel.cleanarchitectureexample.databinding.FragmentLoginBinding
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BaseFragment

class LoginFragment private constructor() : BaseFragment<FragmentLoginBinding>() {

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding = FragmentLoginBinding::inflate

    companion object {

        fun newInstance() = LoginFragment()
    }
}