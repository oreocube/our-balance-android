package com.ourbalance.domain.model.balance

data class BalanceInfo(
    val roomId: Long,
    val roomTitle: String,
    val currentUserCount: Int,
    val maxUserCount: Int
)
