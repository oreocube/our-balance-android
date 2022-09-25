package com.ourbalance.domain.repository

import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceInfo

interface BalanceRepository {
    suspend fun getBalanceList(): List<BalanceInfo>
    suspend fun getBalanceDetail(id: Long): BalanceDetail
}
