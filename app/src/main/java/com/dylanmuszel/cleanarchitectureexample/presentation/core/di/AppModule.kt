package com.dylanmuszel.cleanarchitectureexample.presentation.core.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.dylanmuszel.cleanarchitectureexample.CleanArchitectureExampleApp
import com.dylanmuszel.cleanarchitectureexample.presentation.login.LoginModule
import com.dylanmuszel.cleanarchitectureexample.presentation.posts.PostsModule
import com.dylanmuszel.cleanarchitectureexample.presentation.splash.SplashModule
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        SplashModule::class,
        LoginModule::class,
        PostsModule::class
    ]
)
class AppModule {

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(CleanArchitectureExampleApp.SHARED_PREFERENCES_NAME, MODE_PRIVATE)
}