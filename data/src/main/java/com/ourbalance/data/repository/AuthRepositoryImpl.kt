package com.ourbalance.data.repository

import com.ourbalance.data.source.remote.auth.AuthDataSource
import com.ourbalance.domain.model.auth.UserInfo
import com.ourbalance.domain.repository.AuthRepository
import com.ourbalance.domain.result.Result
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun login(userInfo: UserInfo): Result<Unit> {
        return authDataSource.login(userInfo)
    }

    override suspend fun signup(userInfo: UserInfo): Result<Unit> {
        return authDataSource.signup(userInfo)
    }

    override suspend fun logout() {
        authDataSource.logout()
    }
}
