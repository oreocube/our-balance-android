package com.ourbalance.domain.usecase

import com.ourbalance.domain.di.IoDispatcher
import com.ourbalance.domain.model.room.RoomInfo
import com.ourbalance.domain.repository.BalanceRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AddBalanceUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<RoomInfo, Long>(dispatcher) {

    override suspend fun execute(parameters: RoomInfo): Long {
        return balanceRepository.addBalance(parameters)
    }
}
