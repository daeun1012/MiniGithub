package com.githubio.daeun1012.minigithub.data.remote

import androidx.lifecycle.LiveData
import com.githubio.daeun1012.minigithub.data.remote.models.UserSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("/search/users")
    fun searchUsers(@Query("q") query: String): LiveData<ApiResponse<UserSearchResponse>>

    @GET("/search/users")
    fun searchUsers(@Query("q") query: String, @Query("page") page: Int): Call<UserSearchResponse>

}