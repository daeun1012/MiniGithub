package com.githubio.daeun1012.minigithub.data.local

import androidx.room.Entity

@Entity(primaryKeys = ["login"])
data class LikeUser(
        val id: Int,
        val login: String,
        val avatarUrl: String?,
        val company: String?,
        val reposUrl: String?,
        val blog: String?,
        val score: Float
)