package com.ourbalance.domain.repository

import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.result.Result

interface BalanceRepository {
    suspend fun getBalanceList(): Result<List<BalanceInfo>>
}
