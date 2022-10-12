package com.ourbalance.data.source.remote.payment

import com.ourbalance.data.api.PaymentService
import com.ourbalance.data.entity.mapper.toEntity
import com.ourbalance.data.entity.mapper.toModel
import com.ourbalance.domain.model.payment.PaymentInfo
import com.ourbalance.domain.model.payment.PaymentItemModel
import javax.inject.Inject

class PaymentDataSourceImpl @Inject constructor(
    private val paymentService: PaymentService
) : PaymentDataSource {

    override suspend fun addPayment(paymentInfo: PaymentInfo): Long {
        return paymentService.addPayment(paymentInfo.toEntity()).paymentId
    }

    override suspend fun getAllPayments(
        page: Int,
        balanceId: Long,
        participantId: Long
    ): List<PaymentItemModel.Payment> {
        return paymentService.getAllPaymentsForParticipant(
            page = page,
            balanceId = balanceId,
            pid = participantId
        ).payments.map { it.toModel() }
    }
}
