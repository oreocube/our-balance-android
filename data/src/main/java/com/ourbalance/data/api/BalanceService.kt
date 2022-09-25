package com.ourbalance.data.api

import com.ourbalance.data.entity.balance.BalanceDetailEntity
import com.ourbalance.data.entity.balance.BalanceListResponse
import com.ourbalance.data.entity.room.RoomInfoEntity
import com.ourbalance.data.entity.room.RoomInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BalanceService {
    @GET("/balance")
    fun getBalanceList(): BalanceListResponse

    @GET("/balance/{id}")
    fun getBalanceDetail(
        @Path("id") id: Long
    ): BalanceDetailEntity

    @POST("/balance/create")
    fun createBalance(
        @Body roomInfo: RoomInfoEntity
    ): RoomInfoResponse
}
