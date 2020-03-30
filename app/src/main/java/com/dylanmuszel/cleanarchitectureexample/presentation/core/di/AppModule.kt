package com.dylanmuszel.cleanarchitectureexample.presentation.core.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.dylanmuszel.cleanarchitectureexample.CleanArchitectureExampleApp
import dagger.Module
import dagger.Provides

@Module(includes = [])
class AppModule {

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(CleanArchitectureExampleApp.SHARED_PREFERENCES_NAME, MODE_PRIVATE)
}