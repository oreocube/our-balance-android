package com.ourbalance.data.source.remote.balance

import com.ourbalance.data.api.BalanceService
import com.ourbalance.data.entity.mapper.toModel
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.result.Result
import javax.inject.Inject

class BalanceDataSourceImpl @Inject constructor(
    private val balanceService: BalanceService
) : BalanceDataSource {
    override suspend fun getBalanceList(): Result<List<BalanceInfo>> {
        return try {
            val response = balanceService.getBalanceList()

            if (response.status == 200) {
                Result.Success(response.data!!.balanceList.map {
                    it.toModel()
                })
            } else {
                Result.Error(response.error!!)
            }
        } catch (e: Throwable) {
            Result.Error(e.message ?: "Unknown Error")
        }
    }
}
