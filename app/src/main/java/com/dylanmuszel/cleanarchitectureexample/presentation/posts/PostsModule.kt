package com.dylanmuszel.cleanarchitectureexample.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylanmuszel.cleanarchitectureexample.presentation.core.di.ViewModelKey
import com.dylanmuszel.usecases.login.GetLoggedUserUseCase
import com.dylanmuszel.usecases.login.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [PostsModule.ProvideViewModel::class])
abstract class PostsModule {

    @ContributesAndroidInjector
    abstract fun providesPostsActivity(): PostsActivity

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun providesPostsFragment(): PostsFragment

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(PostsViewModel::class)
        fun providePostsViewModel(
            logoutUseCase: LogoutUseCase,
            getLoggedUserUseCase: GetLoggedUserUseCase
        ): ViewModel = PostsViewModel(logoutUseCase, getLoggedUserUseCase)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun providePostsViewModel(
            factory: ViewModelProvider.Factory,
            target: PostsFragment
        ): PostsViewModel = ViewModelProvider(target, factory).get(PostsViewModel::class.java)
    }
}