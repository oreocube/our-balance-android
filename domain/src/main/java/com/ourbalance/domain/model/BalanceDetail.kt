package com.ourbalance.domain.model

data class BalanceDetail(
    val roomId: Long,
    val roomTitle: String,
    val currentUserCount: Int,
    val maxUserCount: Int,
    val password: String,
    val participants: List<Participant>
)
