package com.ourbalance.domain.repository

import com.ourbalance.domain.model.UserInfo
import com.ourbalance.domain.result.Result

interface AuthRepository {
    suspend fun login(userInfo: UserInfo): Result<Unit>
    suspend fun signup(userInfo: UserInfo): Result<Unit>
    suspend fun checkLogin(): Boolean
}
