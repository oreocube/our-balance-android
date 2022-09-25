package com.ourbalance.data.source.remote.balance

import com.ourbalance.data.api.BalanceService
import com.ourbalance.data.entity.mapper.toEntity
import com.ourbalance.data.entity.mapper.toModel
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.model.RoomInfo
import javax.inject.Inject

class BalanceDataSourceImpl @Inject constructor(
    private val balanceService: BalanceService
) : BalanceDataSource {
    override suspend fun getBalanceList(): List<BalanceInfo> {
        return balanceService.getBalanceList().balanceList.map {
            it.toModel()
        }
    }

    override suspend fun getBalanceDetail(id: Long): BalanceDetail {
        return balanceService.getBalanceDetail(id).toModel()
    }

    override suspend fun addBalance(roomInfo: RoomInfo): Long {
        return balanceService.createBalance(roomInfo.toEntity()).roomId
    }
}
