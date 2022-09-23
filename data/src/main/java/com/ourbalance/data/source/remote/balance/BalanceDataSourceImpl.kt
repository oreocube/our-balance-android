package com.ourbalance.data.source.remote.balance

import com.ourbalance.data.api.BalanceService
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.result.Result
import javax.inject.Inject

class BalanceDataSourceImpl @Inject constructor(
    private val balanceService: BalanceService
) : BalanceDataSource {
    override suspend fun getBalanceList(): Result<List<BalanceInfo>> {
        TODO("Not yet implemented")
    }
}
