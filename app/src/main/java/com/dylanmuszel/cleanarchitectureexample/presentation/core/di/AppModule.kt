package com.dylanmuszel.cleanarchitectureexample.presentation.core.di

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylanmuszel.cleanarchitectureexample.CleanArchitectureExampleApp
import com.dylanmuszel.cleanarchitectureexample.presentation.core.AppViewModelFactory
import com.dylanmuszel.cleanarchitectureexample.presentation.login.LoginModule
import com.dylanmuszel.cleanarchitectureexample.presentation.posts.PostsModule
import com.dylanmuszel.cleanarchitectureexample.presentation.splash.SplashModule
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module(
    includes = [
        SplashModule::class,
        LoginModule::class,
        PostsModule::class
    ]
)
class AppModule {

    @Provides
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory = AppViewModelFactory(providers)

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(CleanArchitectureExampleApp.SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext
}