package com.dylanmuszel.cleanarchitectureexample.presentation.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

abstract class BasePresenter <T> : CoroutineScope by CoroutineScope(Dispatchers.Main) {


    protected var view: T? = null

    fun onCreate(view: T) {
        this.view = view
    }

    fun onDestroy() {
        cancel()
        view = null
    }
}