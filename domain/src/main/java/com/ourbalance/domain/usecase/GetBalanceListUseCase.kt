package com.ourbalance.domain.usecase

import com.ourbalance.domain.di.IoDispatcher
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.repository.BalanceRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBalanceListUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<Unit, List<BalanceInfo>>(dispatcher) {

    override suspend fun execute(parameters: Unit): List<BalanceInfo> {
        return balanceRepository.getBalanceList()
    }
}
