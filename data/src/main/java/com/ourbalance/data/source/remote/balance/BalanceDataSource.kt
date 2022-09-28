package com.ourbalance.data.source.remote.balance

import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceDetailInfo
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.model.ParticipationInfo
import com.ourbalance.domain.model.RoomInfo

interface BalanceDataSource {
    suspend fun getBalanceList(): List<BalanceInfo>
    suspend fun getBalanceDetail(balanceDetailInfo: BalanceDetailInfo): BalanceDetail
    suspend fun addBalance(roomInfo: RoomInfo): Long
    suspend fun participateBalance(participationInfo: ParticipationInfo)
}
