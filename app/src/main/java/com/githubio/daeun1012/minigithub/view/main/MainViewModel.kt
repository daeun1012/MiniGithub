package com.githubio.daeun1012.minigithub.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubio.daeun1012.minigithub.data.repository.GithubUserRepository
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject
constructor(
        private val githubUserRepository: GithubUserRepository
) : ViewModel() {

    private val login: MutableLiveData<String> = MutableLiveData()
    private val page: MutableLiveData<Int> = MutableLiveData()

    init {
        Timber.d("Injection MainViewModel")
    }

//    fun getUserName() = githubUserRepository.getUserName()

}