package com.ourbalance.domain.model.payment

data class PaymentInfo(
    val balanceId: Long,
    val payerId: Long,
    val amount: Long,
    val content: String,
    val date: String
)
