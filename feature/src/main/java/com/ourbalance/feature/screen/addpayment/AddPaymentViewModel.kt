package com.ourbalance.feature.screen.addpayment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.model.balance.BalanceDetail
import com.ourbalance.domain.model.payment.PaymentInfo
import com.ourbalance.domain.result.Result
import com.ourbalance.domain.usecase.AddPaymentUseCase
import com.ourbalance.feature.constant.BALANCE_DETAIL
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

class AddPaymentViewModel @AssistedInject constructor(
    @Assisted(BALANCE_DETAIL) private val balanceDetail: BalanceDetail,
    private val addPaymentUseCase: AddPaymentUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PaymentUiState>(PaymentUiState.UnInitialized)
    val state = _state.asStateFlow()

    private val _nextEvent = MutableSharedFlow<PaymentUiState>()
    val nextEvent = _nextEvent.asSharedFlow()

    private val _prevEvent = MutableSharedFlow<Unit>()
    val prevEvent = _prevEvent.asSharedFlow()

    private val _closeEvent = MutableSharedFlow<Unit>()
    val closeEvent = _closeEvent.asSharedFlow()

    private val _successEvent = MutableSharedFlow<Unit>()
    val successEvent = _successEvent.asSharedFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    val userName = MutableStateFlow(balanceDetail.me.userName)
    val otherName = MutableStateFlow(balanceDetail.others[0].userName)

    val amount = MutableStateFlow<Long?>(null)
    val amountValid = amount.map {
        it != null && it > 0
    }.stateIn(
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500),
        scope = viewModelScope
    )

    val content = MutableStateFlow("")
    val contentValid = content.map {
        it.isNotEmpty()
    }.stateIn(
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500),
        scope = viewModelScope
    )

    val date = MutableStateFlow("2022.10.08")
    val myTurn = MutableStateFlow(false)
    val otherTurn = MutableStateFlow(false)

    private val payerId = myTurn.combine(otherTurn) { me, other ->
        if (me xor other) {
            if (me) {
                balanceDetail.me.participantId
            } else {
                balanceDetail.others[0].participantId
            }
        } else {
            -1
        }
    }.stateIn(
        initialValue = -1,
        started = SharingStarted.WhileSubscribed(500),
        scope = viewModelScope
    )

    val payerValid = payerId.map {
        it > 0
    }.stateIn(
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500),
        scope = viewModelScope
    )

    fun setMyTurn(check: Boolean) {
        myTurn.update { check }
        otherTurn.update { check.not() }
    }

    fun next() {
        viewModelScope.launch {
            _state.update { it.onNext() }
            _nextEvent.emit(state.value)
        }
    }

    fun prev() {
        viewModelScope.launch {
            _state.update { it.onPrev() }
            _prevEvent.emit(Unit)
        }
    }

    fun close() {
        viewModelScope.launch {
            _closeEvent.emit(Unit)
        }
    }

    fun addPayment() {
        val info = PaymentInfo(
            balanceId = balanceDetail.roomId,
            payerId = payerId.value,
            amount = amount.value!!,
            content = content.value,
            date = date.value
        )

        viewModelScope.launch {
            when (val result = addPaymentUseCase(info)) {
                is Result.Success -> {
                    Timber.d(result.data.toString())
                    _successEvent.emit(Unit)
                }
                is Result.Error -> {
                    Timber.d(result.message)
                    _message.emit(result.message)
                }
            }
        }
    }

    companion object {

        fun provideFactory(
            assistedFactory: AddPaymentViewModelFactory,
            balanceDetail: BalanceDetail
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(balanceDetail) as T
            }
        }
    }
}

@AssistedFactory
interface AddPaymentViewModelFactory {
    fun create(
        @Assisted(BALANCE_DETAIL) balanceDetail: BalanceDetail
    ): AddPaymentViewModel
}
