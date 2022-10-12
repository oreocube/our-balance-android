package com.ourbalance.domain.model.balance

import com.ourbalance.domain.model.room.Participant
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
