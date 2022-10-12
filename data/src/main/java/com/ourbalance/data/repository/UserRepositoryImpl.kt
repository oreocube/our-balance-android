package com.ourbalance.data.repository

import com.ourbalance.data.source.remote.auth.AuthDataSource
import com.ourbalance.data.source.remote.user.UserDataSource
import com.ourbalance.domain.repository.UserRepository
import com.ourbalance.domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun getUserInfo(): Flow<Result<String>> {
        return authDataSource.isLogin().map { isLogin ->
            if (isLogin) {
                userDataSource.getUserInfo()
            } else {
                Result.Error("로그인 정보 없음")
            }
        }
    }
}
