package com.dylanmuszel.cleanarchitectureexample.presentation.login

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginModule {

    @ContributesAndroidInjector
    abstract fun providesLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun providesLoginFragment(): LoginFragment
}