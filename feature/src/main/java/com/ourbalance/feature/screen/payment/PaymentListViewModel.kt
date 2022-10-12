package com.ourbalance.feature.screen.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ourbalance.domain.model.payment.PaymentItemModel
import com.ourbalance.domain.repository.PaymentRepository
import com.ourbalance.feature.screen.payment.PaymentListViewModel.Companion.BALANCE_ID
import com.ourbalance.feature.screen.payment.PaymentListViewModel.Companion.PARTICIPANT_ID
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import timber.log.Timber

class PaymentListViewModel @AssistedInject constructor(
    @Assisted(BALANCE_ID) balanceId: Long,
    @Assisted(PARTICIPANT_ID) participantId: Long,
    paymentRepository: PaymentRepository
) : ViewModel() {

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    val pagingData: Flow<PagingData<PaymentItemModel>> =
        paymentRepository.getAllPaymentsForParticipant(
            balanceId = balanceId,
            participantId = participantId
        ).catch { Timber.d(it.message) }.cachedIn(viewModelScope)

    companion object {
        const val BALANCE_ID = "BALANCE_ID"
        const val PARTICIPANT_ID = "PARTICIPANT_ID"

        fun provideFactory(
            assistedFactory: PaymentListViewModelFactory,
            balanceId: Long,
            participantId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(balanceId, participantId) as T
            }
        }
    }
}

@AssistedFactory
interface PaymentListViewModelFactory {
    fun create(
        @Assisted(BALANCE_ID) balanceId: Long,
        @Assisted(PARTICIPANT_ID) participantId: Long
    ): PaymentListViewModel
}
