package com.ourbalance.domain.repository

import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.model.ParticipationInfo
import com.ourbalance.domain.model.RoomInfo

interface BalanceRepository {
    suspend fun getBalanceList(): List<BalanceInfo>
    suspend fun getBalanceDetail(id: Long): BalanceDetail
    suspend fun addBalance(roomInfo: RoomInfo): Long
    suspend fun participateBalance(participationInfo: ParticipationInfo)
}
