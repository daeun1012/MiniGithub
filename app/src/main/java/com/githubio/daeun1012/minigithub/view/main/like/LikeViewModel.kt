package com.githubio.daeun1012.minigithub.view.main.like

import androidx.lifecycle.*
import com.githubio.daeun1012.minigithub.data.local.LikeUser
import com.githubio.daeun1012.minigithub.data.remote.models.User
import com.githubio.daeun1012.minigithub.data.repository.GithubUserRepository
import javax.inject.Inject

class LikeViewModel @Inject constructor(private val userRepository: GithubUserRepository) : ViewModel() {

    lateinit var results: LiveData<List<LikeUser>>

    init {
        fetchLikeUser()
    }


    fun refresh() {
//        _query.value?.let {
//            _query.value = it
//        }
    }

    fun fetchLikeUser() {
        results = userRepository.getAllLikes()
    }

    fun likeUser(user: User) {
        userRepository.likeUser(user)
    }
}