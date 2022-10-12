package com.ourbalance.data.source.remote.balance

import com.ourbalance.domain.model.balance.BalanceDetail
import com.ourbalance.domain.model.balance.BalanceDetailInfo
import com.ourbalance.domain.model.balance.BalanceInfo
import com.ourbalance.domain.model.room.ParticipationInfo
import com.ourbalance.domain.model.room.RoomInfo

interface BalanceDataSource {
    suspend fun getBalanceList(): List<BalanceInfo>
    suspend fun getBalanceDetail(balanceDetailInfo: BalanceDetailInfo): BalanceDetail
    suspend fun addBalance(roomInfo: RoomInfo): Long
    suspend fun participateBalance(participationInfo: ParticipationInfo)
}
