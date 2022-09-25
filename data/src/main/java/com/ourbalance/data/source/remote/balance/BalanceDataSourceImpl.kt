package com.ourbalance.data.source.remote.balance

import com.ourbalance.data.api.BalanceService
import com.ourbalance.data.entity.mapper.toModel
import com.ourbalance.data.ext.getResponse
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceInfo
import javax.inject.Inject

class BalanceDataSourceImpl @Inject constructor(
    private val balanceService: BalanceService
) : BalanceDataSource {
    override suspend fun getBalanceList(): List<BalanceInfo> {
        return getResponse(balanceService.getBalanceList()).balanceList.map {
            it.toModel()
        }
    }

    override suspend fun getBalanceDetail(id: Long): BalanceDetail {
        return getResponse(balanceService.getBalanceDetail(id)).toModel()
    }
}
