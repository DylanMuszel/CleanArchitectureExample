package com.dylanmuszel.cleanarchitectureexample.presentation.splash

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SplashModule {

    @ContributesAndroidInjector
    abstract fun providesSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun providesSplashFragment(): SplashFragment
}