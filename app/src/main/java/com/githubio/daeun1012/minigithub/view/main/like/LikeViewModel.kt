package com.githubio.daeun1012.minigithub.view.main.like

import androidx.lifecycle.ViewModel
import com.githubio.daeun1012.minigithub.data.repository.GithubUserRepository
import javax.inject.Inject

class LikeViewModel @Inject
constructor(
        private val githubUserRepository: GithubUserRepository
) : ViewModel() {

}