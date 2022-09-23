package com.ourbalance.data.entity.balance

import com.google.gson.annotations.SerializedName

data class BalanceListResponse(
    @SerializedName("listCount")
    val listCount: Int,
    @SerializedName("balanceList")
    val balanceList: List<BalanceInfoEntity>
)
