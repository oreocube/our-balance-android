package com.ourbalance.data.source.remote.auth

import com.ourbalance.domain.model.auth.UserInfo
import com.ourbalance.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun login(userInfo: UserInfo): Result<Unit>
    suspend fun signup(userInfo: UserInfo): Result<Unit>
    suspend fun saveToken(token: String)
    suspend fun getToken(): String
    suspend fun isLogin(): Flow<Boolean>
    suspend fun logout()
}
