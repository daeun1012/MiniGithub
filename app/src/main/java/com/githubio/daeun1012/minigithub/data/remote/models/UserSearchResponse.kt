package com.githubio.daeun1012.minigithub.data.remote.models

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(
        @SerializedName("total_count")
        val total: Int = 0,
        @SerializedName("items")
        val items: List<User>
) {
    var nextPage: Int? = null
}
