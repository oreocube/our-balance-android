package com.ourbalance.domain.model

import java.io.Serializable

data class BalanceDetail(
    val roomId: Long,
    val roomTitle: String,
    val isFull: Boolean,
    val password: String,
    val total: Long,
    val me: Participant,
    val others: List<Participant>
) : Serializable
