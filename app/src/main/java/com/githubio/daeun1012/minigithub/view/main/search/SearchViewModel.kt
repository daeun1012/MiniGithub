package com.githubio.daeun1012.minigithub.view.main.search

import androidx.lifecycle.ViewModel
import com.githubio.daeun1012.minigithub.data.repository.GithubUserRepository
import javax.inject.Inject

class SearchViewModel @Inject
constructor(
        private val githubUserRepository: GithubUserRepository
) : ViewModel() {

}