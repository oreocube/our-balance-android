package com.ourbalance.domain.usecase

import com.ourbalance.domain.di.IoDispatcher
import com.ourbalance.domain.repository.UserRepository
import com.ourbalance.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<Result<String>> {
        return userRepository.getUserInfo()
            .catch { e -> Result.Error(e.message ?: "Unknown Error") }
            .flowOn(dispatcher)
    }
}
