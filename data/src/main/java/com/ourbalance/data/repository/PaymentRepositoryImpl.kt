package com.ourbalance.data.repository

import com.ourbalance.data.source.remote.payment.PaymentDataSource
import com.ourbalance.domain.model.PaymentInfo
import com.ourbalance.domain.repository.PaymentRepository
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val paymentDataSource: PaymentDataSource
) : PaymentRepository {
    override suspend fun addPayment(paymentInfo: PaymentInfo): Long {
        return paymentDataSource.addPayment(paymentInfo)
    }
}
