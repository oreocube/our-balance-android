package com.ourbalance.domain.repository

import com.ourbalance.domain.model.*

interface BalanceRepository {
    suspend fun getBalanceList(): List<BalanceInfo>
    suspend fun getBalanceDetail(balanceDetailInfo: BalanceDetailInfo): BalanceDetail
    suspend fun addBalance(roomInfo: RoomInfo): Long
    suspend fun participateBalance(participationInfo: ParticipationInfo)
}
