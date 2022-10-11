package com.ourbalance.domain.repository

import androidx.paging.PagingData
import com.ourbalance.domain.model.PaymentInfo
import com.ourbalance.domain.model.PaymentItemModel
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    suspend fun addPayment(paymentInfo: PaymentInfo): Long
    fun getAllPaymentsForParticipant(
        balanceId: Long,
        participantId: Long
    ): Flow<PagingData<PaymentItemModel>>
}
