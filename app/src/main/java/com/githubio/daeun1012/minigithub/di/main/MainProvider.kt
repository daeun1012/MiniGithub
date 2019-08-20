package com.githubio.daeun1012.minigithub.di.main

import com.githubio.daeun1012.minigithub.view.main.MainActivity
import com.githubio.daeun1012.minigithub.view.main.MainPagerAdapter
import dagger.Module
import dagger.Provides

@Module
class MainProvider {

    @Provides
    fun providePagerAdapter(activity: MainActivity): MainPagerAdapter {
        return MainPagerAdapter(activity.supportFragmentManager)
    }

}