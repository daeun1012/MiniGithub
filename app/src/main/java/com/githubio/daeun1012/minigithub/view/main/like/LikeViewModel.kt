package com.githubio.daeun1012.minigithub.view.main.like

import androidx.lifecycle.*
import com.githubio.daeun1012.minigithub.data.local.LikeUser
import com.githubio.daeun1012.minigithub.data.remote.models.Resource
import com.githubio.daeun1012.minigithub.data.remote.models.User
import com.githubio.daeun1012.minigithub.data.repository.GithubUserRepository
import javax.inject.Inject

class LikeViewModel @Inject constructor(private val userRepository: GithubUserRepository) : ViewModel() {

    val results: MutableLiveData<List<LikeUser>> = MutableLiveData()

    fun refresh() {
//        _query.value?.let {
//            _query.value = it
//        }
    }

    fun fetchLikeUser() {
        results.value = userRepository.getAllLikes().value
    }

    fun likeUser(user: User) {
        userRepository.likeUser(user)
    }
}