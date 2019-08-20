package com.sadmansarar.app.latemailsend.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sadmansarar.app.latemailsend.viewmodel.ViewModelFactory
import com.sadmansarar.app.latemailsend.viewmodel.implimentation.IRepoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(IRepoViewModel::class)
    internal abstract fun bindRepoViewModel(repoViewModel: IRepoViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}