package com.githubio.daeun1012.minigithub.di.main

import com.githubio.daeun1012.minigithub.view.main.like.LikeFragment
import com.githubio.daeun1012.minigithub.view.main.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    internal abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    internal abstract fun contributeLikeFragment(): LikeFragment
}