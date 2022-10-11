package com.ourbalance.feature.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ourbalance.domain.model.PaymentItemModel
import com.ourbalance.domain.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PaymentListViewModel @Inject constructor(
    paymentRepository: PaymentRepository
) : ViewModel() {

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    val pagingData: Flow<PagingData<PaymentItemModel>> =
        paymentRepository.getAllPaymentsForParticipant(
            balanceId = 100, participantId = 1
        ).catch { Timber.d(it.message) }.cachedIn(viewModelScope)

}
