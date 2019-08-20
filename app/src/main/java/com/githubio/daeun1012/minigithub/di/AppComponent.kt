package com.githubio.daeun1012.minigithub.di

import android.app.Application
import com.githubio.daeun1012.minigithub.MiniGithubApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidInjectionModule::class),
    (ActivityModule::class),
    (ViewModelModule::class),
    (AppModule::class)])
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication)
}