package com.ourbalance.data.source.remote.auth

import com.ourbalance.domain.model.auth.UserInfo
import com.ourbalance.domain.result.Result

interface AuthDataSource {
    suspend fun login(userInfo: UserInfo): Result<Unit>
    suspend fun signup(userInfo: UserInfo): Result<Unit>
    suspend fun saveToken(token: String)
    suspend fun getToken(): String
}
