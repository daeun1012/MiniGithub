package com.githubio.daeun1012.minigithub.data.repository

import com.githubio.daeun1012.minigithub.data.remote.GithubService
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserRepository @Inject
constructor(
//        private val githubUserDao: GithubUserDao,
//        private val followersDao: FollowersDao,
        private val service: GithubService) {

//    @InjectPreference
//    lateinit var profile: Preference_UserProfile

    init {
        Timber.d("Injection GithubUserRepository")
    }

//    fun getUserName(): String = profile.name!!

    companion object {
        const val per_page = 10
    }
}