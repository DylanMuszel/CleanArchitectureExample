package com.dylanmuszel.cleanarchitectureexample.presentation.core

import android.os.Bundle
import androidx.fragment.app.commit
import com.dylanmuszel.cleanarchitectureexample.R
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity(private val fragment: BaseFragment<*>) : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        supportFragmentManager.commit { replace(R.id.base_content, fragment) }
    }
}