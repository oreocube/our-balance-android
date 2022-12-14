package com.ourbalance.domain.usecase

import com.ourbalance.domain.di.IoDispatcher
import com.ourbalance.domain.model.auth.UserInfo
import com.ourbalance.domain.repository.AuthRepository
import com.ourbalance.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(userInfo: UserInfo): Result<Unit> {
        return try {
            withContext(dispatcher) {
                authRepository.login(userInfo)
            }
        } catch (e: Throwable) {
            Result.Error(e.message ?: "Unknown Error")
        }
    }
}
