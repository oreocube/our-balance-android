package com.ourbalance.data.source.remote.payment

import com.ourbalance.domain.model.PaymentInfo

interface PaymentDataSource {
    suspend fun addPayment(paymentInfo: PaymentInfo): Long
}
