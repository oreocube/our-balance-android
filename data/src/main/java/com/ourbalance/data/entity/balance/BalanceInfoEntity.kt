package com.ourbalance.data.entity.balance

import com.google.gson.annotations.SerializedName

data class BalanceInfoEntity(
    @SerializedName("balanceid")
    val roomId: Long,
    @SerializedName("title")
    val roomTitle: String,
    @SerializedName("currentuser")
    val currentUserCount: Int,
    @SerializedName("maxuser")
    val maxUserCount: Int
)
