package com.ourbalance.data.repository

import com.ourbalance.data.source.remote.balance.BalanceDataSource
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.repository.BalanceRepository
import com.ourbalance.domain.result.Result
import javax.inject.Inject

class BalanceRepositoryImpl @Inject constructor(
    private val balanceDataSource: BalanceDataSource
) : BalanceRepository {
    override suspend fun getBalanceList(): Result<List<BalanceInfo>> {
        return balanceDataSource.getBalanceList()
    }

    override suspend fun getBalanceDetail(id: Long): Result<BalanceDetail> {
        return balanceDataSource.getBalanceDetail(id)
    }
}
