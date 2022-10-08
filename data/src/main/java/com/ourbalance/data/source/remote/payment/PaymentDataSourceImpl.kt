package com.ourbalance.data.source.remote.payment

import com.ourbalance.data.api.PaymentService
import com.ourbalance.data.entity.mapper.toEntity
import com.ourbalance.domain.model.PaymentInfo
import javax.inject.Inject

class PaymentDataSourceImpl @Inject constructor(
    private val paymentService: PaymentService
) : PaymentDataSource {

    override suspend fun addPayment(paymentInfo: PaymentInfo): Long {
        return paymentService.addPayment(paymentInfo.toEntity()).paymentId
    }
}
