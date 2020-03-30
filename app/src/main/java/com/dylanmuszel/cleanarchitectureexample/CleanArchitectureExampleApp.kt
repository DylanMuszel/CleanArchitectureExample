package com.dylanmuszel.cleanarchitectureexample

import com.dylanmuszel.cleanarchitectureexample.presentation.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CleanArchitectureExampleApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<CleanArchitectureExampleApp> = DaggerAppComponent
        .builder()
        .sharedPreferencesName(SHARED_PREFERENCES_NAME)
        .application(this)
        .create(this)

    companion object {

        const val SHARED_PREFERENCES_NAME = "CLEAN_ARCH_SHARED_PREFERENCES"
    }
}