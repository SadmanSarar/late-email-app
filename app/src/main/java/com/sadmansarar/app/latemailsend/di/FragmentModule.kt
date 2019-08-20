package com.sadmansarar.app.latemailsend.di

import com.sadmansarar.app.latemailsend.view.fragments.HomeFragment
import com.sadmansarar.app.latemailsend.view.fragments.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di
 */
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSettingsFragment(): SettingsFragment
}
