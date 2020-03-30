package com.dylanmuszel.cleanarchitectureexample.presentation.posts

import android.content.Context
import android.content.Intent
import com.dylanmuszel.cleanarchitectureexample.presentation.core.BaseActivity

class PostsActivity : BaseActivity(PostsFragment.newInstance()) {

    companion object {

        fun getStartIntent(context: Context) = Intent(context, PostsActivity::class.java)
    }
}
