package com.ourbalance.domain.usecase

import com.ourbalance.domain.di.IoDispatcher
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceDetailInfo
import com.ourbalance.domain.repository.BalanceRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class GetBalanceListUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<Long, List<BalanceDetail>>(dispatcher) {

    override suspend fun execute(parameters: Long): List<BalanceDetail> {
        val result = mutableListOf<Deferred<BalanceDetail>>()
        val list = balanceRepository.getBalanceList()

        coroutineScope {
            list.forEach { balanceInfo ->
                val item = async {
                    balanceRepository.getBalanceDetail(
                        BalanceDetailInfo(
                            userId = parameters,
                            balanceId = balanceInfo.roomId
                        )
                    )
                }
                result.add(item)
            }
        }
        return result.awaitAll()
    }
}