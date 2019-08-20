package com.githubio.daeun1012.minigithub

import com.facebook.stetho.Stetho
import com.githubio.daeun1012.minigithub.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class MiniGithubApp : DaggerApplication() {

    private val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Stetho.initializeWithDefaults(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }
}