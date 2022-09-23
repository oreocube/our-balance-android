package com.ourbalance.data.api

import com.ourbalance.data.entity.BaseResponse
import com.ourbalance.data.entity.balance.BalanceDetailEntity
import com.ourbalance.data.entity.balance.BalanceListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BalanceService {
    @GET("/balance")
    fun getBalanceList(): BaseResponse<BalanceListResponse>

    @GET("/balance/{id}")
    fun getBalanceDetail(
        @Path("id") id: Long
    ): BaseResponse<BalanceDetailEntity>
}
