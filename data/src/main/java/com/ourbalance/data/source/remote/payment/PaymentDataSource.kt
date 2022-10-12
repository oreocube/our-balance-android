package com.ourbalance.data.source.remote.payment

import com.ourbalance.domain.model.payment.PaymentInfo
import com.ourbalance.domain.model.payment.PaymentItemModel

interface PaymentDataSource {
    suspend fun addPayment(paymentInfo: PaymentInfo): Long
    suspend fun getAllPayments(
        page: Int,
        balanceId: Long,
        participantId: Long
    ): List<PaymentItemModel.Payment>
}
