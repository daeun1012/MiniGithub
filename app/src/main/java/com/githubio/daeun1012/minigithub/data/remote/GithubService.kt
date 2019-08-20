package com.githubio.daeun1012.minigithub.data.remote

import androidx.lifecycle.LiveData
import com.githubio.daeun1012.minigithub.data.remote.models.GithubUser
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("/v3/search/users")
    fun fetchGithubUser(@Query("q") query: String): LiveData<ApiResponse<GithubUser>>

}