package com.ourbalance.data.source.remote.balance

import com.ourbalance.data.api.BalanceService
import com.ourbalance.data.entity.mapper.toEntity
import com.ourbalance.data.entity.mapper.toModel
import com.ourbalance.domain.model.balance.BalanceDetail
import com.ourbalance.domain.model.balance.BalanceDetailInfo
import com.ourbalance.domain.model.balance.BalanceInfo
import com.ourbalance.domain.model.room.ParticipationInfo
import com.ourbalance.domain.model.room.RoomInfo
import javax.inject.Inject

class BalanceDataSourceImpl @Inject constructor(
    private val balanceService: BalanceService
) : BalanceDataSource {
    override suspend fun getBalanceList(): List<BalanceInfo> {
        return balanceService.getBalanceList().balanceList.map {
            it.toModel()
        }
    }

    override suspend fun getBalanceDetail(balanceDetailInfo: BalanceDetailInfo): BalanceDetail {
        return balanceService.getBalanceDetail(balanceDetailInfo.balanceId)
            .toModel(balanceDetailInfo.userName)
    }

    override suspend fun addBalance(roomInfo: RoomInfo): Long {
        return balanceService.createBalance(roomInfo.toEntity()).roomId
    }

    override suspend fun participateBalance(participationInfo: ParticipationInfo) {
        balanceService.participateBalance(participationInfo.toEntity())
    }
}
