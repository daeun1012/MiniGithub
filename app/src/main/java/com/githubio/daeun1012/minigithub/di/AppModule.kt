package com.githubio.daeun1012.minigithub.di

import androidx.annotation.NonNull
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.githubio.daeun1012.minigithub.data.remote.GithubService
import com.githubio.daeun1012.minigithub.data.remote.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
    }

    @Provides
    @Singleton
    fun provideGithubService(@NonNull retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }
}