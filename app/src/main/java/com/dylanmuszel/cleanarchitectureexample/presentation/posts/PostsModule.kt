package com.dylanmuszel.cleanarchitectureexample.presentation.posts

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PostsModule {

    @ContributesAndroidInjector
    abstract fun providesPostsActivity(): PostsActivity

    @ContributesAndroidInjector
    abstract fun providesPostsFragment(): PostsFragment
}