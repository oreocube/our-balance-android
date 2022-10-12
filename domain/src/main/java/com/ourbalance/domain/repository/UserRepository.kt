package com.ourbalance.domain.repository

import com.ourbalance.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserInfo(): Flow<Result<String>>
}
