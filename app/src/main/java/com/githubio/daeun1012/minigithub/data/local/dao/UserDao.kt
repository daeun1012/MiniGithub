/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.githubio.daeun1012.minigithub.data.local.dao

import android.util.SparseIntArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.*
import com.githubio.daeun1012.minigithub.data.local.LikeUser
import com.githubio.daeun1012.minigithub.data.remote.models.User
import com.githubio.daeun1012.minigithub.data.remote.models.UserSearchResult
import io.reactivex.Completable
import java.util.*

/**
 * Interface for database access for User related operations.
 */
@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(user: User): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(result: UserSearchResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUsers(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun like(user: LikeUser)

    @Delete
    abstract fun unLike(user: LikeUser)

    @Query("SELECT * FROM user WHERE login = :login")
    abstract fun findByLogin(login: String): LiveData<User>

    @Query("SELECT * FROM UserSearchResult WHERE `query` = :query")
    abstract fun findSearchResult(query: String): UserSearchResult?

    @Query("SELECT * FROM UserSearchResult WHERE `query` = :query")
    abstract fun search(query: String): LiveData<UserSearchResult>

    fun loadOrdered(repoIds: List<Int>): LiveData<List<User>> {
        val order = SparseIntArray()
        repoIds.withIndex().forEach {
            order.put(it.value, it.index)
        }
        return Transformations.map(loadById(repoIds)) { repositories ->
            Collections.sort(repositories) { r1, r2 ->
                val pos1 = order.get(r1.id)
                val pos2 = order.get(r2.id)
                pos1 - pos2
            }
            repositories
        }
    }

    @Query("SELECT * FROM User WHERE id in (:userIds)")
    protected abstract fun loadById(userIds: List<Int>): LiveData<List<User>>
}
