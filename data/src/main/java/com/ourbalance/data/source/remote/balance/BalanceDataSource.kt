package com.ourbalance.data.source.remote.balance

import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.model.RoomInfo

interface BalanceDataSource {
    suspend fun getBalanceList(): List<BalanceInfo>
    suspend fun getBalanceDetail(id: Long): BalanceDetail
    suspend fun addBalance(roomInfo: RoomInfo): Long
}
