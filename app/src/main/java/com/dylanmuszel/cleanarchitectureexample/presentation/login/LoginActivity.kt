package com.dylanmuszel.cleanarchitectureexample.presentation.login

import android.content.Context
import android.content.Intent
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BaseActivity

class LoginActivity : BaseActivity(LoginFragment.newInstance()) {

    companion object {

        fun getStartIntent(context: Context) = Intent(context, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
}
