package com.ourbalance.data.entity.balance

import com.google.gson.annotations.SerializedName

data class BalanceDetailEntity(
    @SerializedName("balanceid")
    val roomId: Long,
    @SerializedName("title")
    val roomTitle: String,
    @SerializedName("currentuser")
    val currentUserCount: Int,
    @SerializedName("maxuser")
    val maxUserCount: Int,
    @SerializedName("password")
    val password: String,
    @SerializedName("sum")
    val total: Long,
    @SerializedName("userList")
    val participants: List<ParticipantEntity>
)
