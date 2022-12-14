package com.ourbalance.data.repository

import com.ourbalance.data.source.remote.balance.BalanceDataSource
import com.ourbalance.domain.model.balance.BalanceDetail
import com.ourbalance.domain.model.balance.BalanceDetailInfo
import com.ourbalance.domain.model.balance.BalanceInfo
import com.ourbalance.domain.model.room.ParticipationInfo
import com.ourbalance.domain.model.room.RoomInfo
import com.ourbalance.domain.repository.BalanceRepository
import javax.inject.Inject

class BalanceRepositoryImpl @Inject constructor(
    private val balanceDataSource: BalanceDataSource
) : BalanceRepository {
    override suspend fun getBalanceList(): List<BalanceInfo> {
        return balanceDataSource.getBalanceList()
    }

    override suspend fun getBalanceDetail(balanceDetailInfo: BalanceDetailInfo): BalanceDetail {
        return balanceDataSource.getBalanceDetail(balanceDetailInfo)
    }

    override suspend fun addBalance(roomInfo: RoomInfo): Long {
        return balanceDataSource.addBalance(roomInfo)
    }

    override suspend fun participateBalance(participationInfo: ParticipationInfo) {
        balanceDataSource.participateBalance(participationInfo)
    }
}
