package com.dylanmuszel.cleanarchitectureexample.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylanmuszel.cleanarchitectureexample.presentation.core.di.ViewModelKey
import com.dylanmuszel.usecases.login.GetLoggedUserUseCase
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [SplashModule.ProvideViewModel::class])
abstract class SplashModule {

    @ContributesAndroidInjector
    abstract fun providesSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun providesSplashFragment(): SplashFragment

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(SplashViewModel::class)
        fun provideSplashViewModel(getLoggedUserUseCase: GetLoggedUserUseCase): ViewModel = SplashViewModel(getLoggedUserUseCase)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideSplashViewModel(
            factory: ViewModelProvider.Factory,
            target: SplashFragment
        ): SplashViewModel = ViewModelProvider(target, factory).get(SplashViewModel::class.java)
    }
}