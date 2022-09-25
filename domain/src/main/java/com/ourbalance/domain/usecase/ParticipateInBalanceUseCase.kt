package com.ourbalance.domain.usecase

import com.ourbalance.domain.di.IoDispatcher
import com.ourbalance.domain.model.ParticipationInfo
import com.ourbalance.domain.repository.BalanceRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ParticipateInBalanceUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<ParticipationInfo, Unit>(dispatcher) {

    override suspend fun execute(parameters: ParticipationInfo) {
        balanceRepository.participateBalance(parameters)
    }
}
