package com.ourbalance.data.source.remote.user

import com.ourbalance.domain.result.Result

interface UserDataSource {
    suspend fun getUserInfo(): Result<String>
    suspend fun saveUserInfo(name: String)
}
