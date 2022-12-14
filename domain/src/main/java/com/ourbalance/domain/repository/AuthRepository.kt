package com.ourbalance.domain.repository

import com.ourbalance.domain.model.auth.UserInfo
import com.ourbalance.domain.result.Result

interface AuthRepository {
    suspend fun login(userInfo: UserInfo): Result<Unit>
    suspend fun signup(userInfo: UserInfo): Result<Unit>
    suspend fun logout()
}
