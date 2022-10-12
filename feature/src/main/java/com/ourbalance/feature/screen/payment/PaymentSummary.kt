package com.ourbalance.feature.screen.payment

import java.io.Serializable

data class PaymentSummary(
    val isMe: Boolean,
    val balanceId: Long,
    val payerId: Long,
    val username: String,
    val total: Long,
    val amount: Long,
    val ratio: Int
) : Serializable
