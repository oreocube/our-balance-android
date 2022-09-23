package com.ourbalance.data.source.remote.balance

import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.result.Result

interface BalanceDataSource {
    suspend fun getBalanceList(): Result<List<BalanceInfo>>
    suspend fun getBalanceDetail(id: Long): Result<BalanceDetail>
}
