package com.dylanmuszel.cleanarchitectureexample.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.dylanmuszel.cleanarchitectureexample.presentation.login.LoginPresenter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T : ViewBinding, P : BasePresenter<*>> : DaggerFragment() {

    @Inject
    protected lateinit var fragmentHandler: FragmentHandler<P>

    protected val presenter: P get() = fragmentHandler.presenter

    private var _binding: T? = null
    protected val binding get() = _binding!!

    abstract val inflate: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> T

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentHandler.onCreate(this)
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentHandler.onDestroy()
    }
}