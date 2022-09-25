package com.ourbalance.domain.usecase

import com.ourbalance.domain.di.IoDispatcher
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.repository.BalanceRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBalanceDetailUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : UseCase<Long, BalanceDetail>(dispatcher) {

    override suspend fun execute(parameters: Long): BalanceDetail {
        return balanceRepository.getBalanceDetail(parameters)
    }
}
