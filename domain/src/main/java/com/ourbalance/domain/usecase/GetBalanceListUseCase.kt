package com.ourbalance.domain.usecase

import com.ourbalance.domain.di.IoDispatcher
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.repository.BalanceRepository
import com.ourbalance.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBalanceListUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Result<List<BalanceInfo>> {
        return try {
            withContext(dispatcher) {
                balanceRepository.getBalanceList()
            }
        } catch (e: Throwable) {
            Result.Error(e.message ?: "Unknown Error")
        }
    }
}