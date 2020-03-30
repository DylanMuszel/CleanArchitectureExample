package com.dylanmuszel.cleanarchitectureexample.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylanmuszel.cleanarchitectureexample.presentation.core.di.ViewModelKey
import com.dylanmuszel.usecases.login.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [LoginModule.ProvideViewModel::class])
abstract class LoginModule {

    @ContributesAndroidInjector
    abstract fun providesLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun providesLoginFragment(): LoginFragment

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(LoginViewModel::class)
        fun provideLoginViewModel(loginUseCase: LoginUseCase): ViewModel = LoginViewModel(loginUseCase)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideLoginViewModel(
            factory: ViewModelProvider.Factory,
            target: LoginFragment
        ): LoginViewModel = ViewModelProvider(target, factory).get(LoginViewModel::class.java)
    }
}