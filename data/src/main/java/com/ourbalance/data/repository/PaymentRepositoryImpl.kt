package com.ourbalance.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ourbalance.data.source.remote.payment.PaymentDataSource
import com.ourbalance.data.source.remote.payment.PaymentPagingSource
import com.ourbalance.domain.model.PaymentInfo
import com.ourbalance.domain.model.PaymentItemModel
import com.ourbalance.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val paymentDataSource: PaymentDataSource
) : PaymentRepository {
    override suspend fun addPayment(paymentInfo: PaymentInfo): Long {
        return paymentDataSource.addPayment(paymentInfo)
    }

    override fun getAllPaymentsForParticipant(
        balanceId: Long,
        participantId: Long
    ): Flow<PagingData<PaymentItemModel>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                PaymentPagingSource(
                    paymentDataSource = paymentDataSource,
                    balanceId = balanceId,
                    participantId = participantId
                )
            }
        ).flow
    }
}
