package com.ourbalance.domain.repository

import com.ourbalance.domain.model.PaymentInfo

interface PaymentRepository {
    suspend fun addPayment(paymentInfo: PaymentInfo): Long
}
