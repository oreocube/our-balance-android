package com.ourbalance.feature.screen.payment

import java.io.Serializable

data class PaymentSummary(
    val isMe: Boolean,
    val username: String,
    val total: Long,
    val amount: Long,
    val ratio: Int
) : Serializable