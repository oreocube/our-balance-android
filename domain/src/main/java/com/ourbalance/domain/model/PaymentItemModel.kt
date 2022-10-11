package com.ourbalance.domain.model

sealed class PaymentItemModel(open val date: String) {
    data class Payment(
        val paymentId: Long,
        val participantId: Long,
        val content: String,
        override val date: String,
        val amount: Long,
        val username: String
    ) : PaymentItemModel(date)

    data class Header(
        override val date: String
    ) : PaymentItemModel(date)
}
