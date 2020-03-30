package com.dylanmuszel.cleanarchitectureexample.presentation.core.di

import android.app.Application
import com.dylanmuszel.cleanarchitectureexample.CleanArchitectureExampleApp
import com.dylanmuszel.cleanarchitectureexample.framework.login.UserModule
import com.dylanmuszel.cleanarchitectureexample.presentation.login.LoginModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        UserModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<CleanArchitectureExampleApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<CleanArchitectureExampleApp>() {

        @BindsInstance
        abstract fun application(application: Application): Builder

        @BindsInstance
        abstract fun sharedPreferencesName(sharedPrefName: String): Builder
    }
}
