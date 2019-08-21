package com.githubio.daeun1012.minigithub.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.githubio.daeun1012.minigithub.AppExecutors
import com.githubio.daeun1012.minigithub.data.local.GithubDb
import com.githubio.daeun1012.minigithub.data.local.LikeUser
import com.githubio.daeun1012.minigithub.data.local.dao.UserDao
import com.githubio.daeun1012.minigithub.data.remote.ApiSuccessResponse
import com.githubio.daeun1012.minigithub.data.remote.GithubService
import com.githubio.daeun1012.minigithub.data.remote.models.Resource
import com.githubio.daeun1012.minigithub.data.remote.models.User
import com.githubio.daeun1012.minigithub.data.remote.models.UserSearchResponse
import com.githubio.daeun1012.minigithub.data.remote.models.UserSearchResult
import com.githubio.daeun1012.minigithub.util.AbsentLiveData
import com.githubio.daeun1012.minigithub.util.RateLimiter
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val db: GithubDb,
        private val userDao: UserDao,
        private val githubService: GithubService) {

    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    init {
        Timber.d("Injection GithubUserRepository")
    }

    fun searchNextPage(query: String): LiveData<Resource<Boolean>> {
        val fetchNextSearchPageTask = FetchNextSearchPageTask(
                query = query,
                githubService = githubService,
                db = db
        )
        appExecutors.networkIO().execute(fetchNextSearchPageTask)
        return fetchNextSearchPageTask.liveData
    }

    fun likeUser(user: User) {
        appExecutors.diskIO().execute {
            db.runInTransaction {
                db.userDao().updateUser(user)
                if (user.isLike) {
                    db.userDao().like(LikeUser(user.id, user.login, user.avatarUrl, user.company, user.reposUrl, user.blog, user.score))
                } else {
                    db.userDao().unLike(LikeUser(user.id, user.login, user.avatarUrl, user.company, user.reposUrl, user.blog, user.score))
                }
            }

        }
    }

    fun getAllLikes(): LiveData<List<LikeUser>> {
        return db.userDao().getAllLikes()
    }

    fun search(query: String): LiveData<Resource<List<User>>> {
        return object : NetworkBoundResource<List<User>, UserSearchResponse>(appExecutors) {

            override fun saveCallResult(item: UserSearchResponse) {
                val repoIds = item.items.map { it.id }
                val repoSearchResult = UserSearchResult(
                        query = query,
                        userIds = repoIds,
                        totalCount = item.total,
                        next = item.nextPage
                )
                db.runInTransaction {
                    userDao.insertUsers(item.items)
                    userDao.insert(repoSearchResult)
                }
            }

            override fun shouldFetch(data: List<User>?) = data == null

            override fun loadFromDb(): LiveData<List<User>> {
                return Transformations.switchMap(userDao.search(query)) { searchData ->
                    if (searchData == null) {
                        AbsentLiveData.create()
                    } else {
                        userDao.loadOrdered(searchData.userIds)
                    }
                }
            }

            override fun createCall() = githubService.searchUsers(query)

            override fun processResponse(response: ApiSuccessResponse<UserSearchResponse>)
                    : UserSearchResponse {
                val body = response.body
                body.nextPage = response.nextPage
                return body
            }
        }.asLiveData()
    }
}