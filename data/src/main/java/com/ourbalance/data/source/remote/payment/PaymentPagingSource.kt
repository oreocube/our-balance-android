package com.ourbalance.data.source.remote.payment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ourbalance.domain.model.PaymentItemModel
import javax.inject.Inject

class PaymentPagingSource @Inject constructor(
    private val paymentDataSource: PaymentDataSource,
    private val balanceId: Long,
    private val participantId: Long
) : PagingSource<Int, PaymentItemModel>() {

    private lateinit var last: PaymentItemModel

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PaymentItemModel> {
        val page = params.key ?: STARTING_KEY

        val result = paymentDataSource.getAllPayments(
            page = page,
            balanceId = balanceId,
            participantId = participantId
        )

        val payments = mutableListOf<PaymentItemModel>()

        if (result.isNotEmpty()) {
            if (this::last.isInitialized.not()) {
                last = result.first()
                payments.add(PaymentItemModel.Header(last.date))
            }

            result.forEach { payment ->
                if (last.date != payment.date) {
                    last = payment
                    payments.add(PaymentItemModel.Header(last.date))
                }
                payments.add(payment)
            }
        }

        return LoadResult.Page(
            data = payments,
            prevKey = when (page) {
                STARTING_KEY -> null
                else -> page - 1
            },
            nextKey = if (payments.isEmpty()) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PaymentItemModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val STARTING_KEY = 0
    }
}
