package com.ourbalance.data.api

import com.ourbalance.data.entity.BaseResponse
import com.ourbalance.data.entity.balance.BalanceListResponse
import retrofit2.http.GET

interface BalanceService {
    @GET("/balance")
    fun getBalanceList(): BaseResponse<BalanceListResponse>
}
