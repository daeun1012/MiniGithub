package com.githubio.daeun1012.minigithub.di

import com.githubio.daeun1012.minigithub.di.main.MainModule
import com.githubio.daeun1012.minigithub.di.main.MainProvider
import com.githubio.daeun1012.minigithub.view.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainModule::class, MainProvider::class])
    internal abstract fun contributeMainActivity(): MainActivity

}