package com.githubio.daeun1012.minigithub.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.githubio.daeun1012.minigithub.view.main.MainViewModel
import com.githubio.daeun1012.minigithub.view.main.like.LikeViewModel
import com.githubio.daeun1012.minigithub.view.main.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LikeViewModel::class)
    internal abstract fun bindLikeViewModel(likeViewModel: LikeViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(appViewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}