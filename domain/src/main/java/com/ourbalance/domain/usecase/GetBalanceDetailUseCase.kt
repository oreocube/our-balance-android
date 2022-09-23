package com.ourbalance.domain.usecase

import com.ourbalance.domain.di.IoDispatcher
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.repository.BalanceRepository
import com.ourbalance.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBalanceDetailUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: Long): Result<BalanceDetail> {
        return try {
            withContext(dispatcher) {
                balanceRepository.getBalanceDetail(id)
            }
        } catch (e: Throwable) {
            Result.Error(e.message ?: "Unknown Error")
        }
    }
}
