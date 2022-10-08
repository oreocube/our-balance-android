package com.ourbalance.feature.addpayment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.feature.constant.BALANCE_DETAIL
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddPaymentViewModel @AssistedInject constructor(
    @Assisted(BALANCE_DETAIL) private val balanceDetail: BalanceDetail
) : ViewModel() {

    private val _state = MutableStateFlow<PaymentUiState>(PaymentUiState.UnInitialized)
    val state = _state.asStateFlow()

    private val _nextEvent = MutableSharedFlow<PaymentUiState>()
    val nextEvent = _nextEvent.asSharedFlow()

    private val _prevEvent = MutableSharedFlow<Unit>()
    val prevEvent = _prevEvent.asSharedFlow()

    private val _closeEvent = MutableSharedFlow<Unit>()
    val closeEvent = _closeEvent.asSharedFlow()

    val userName = MutableStateFlow(balanceDetail.me.userName)
    val otherName = MutableStateFlow(balanceDetail.others[0].userName)

    val amount = MutableStateFlow(50000)
    val content = MutableStateFlow("")
    val date = MutableStateFlow("2022.10.08")
    val myTurn = MutableStateFlow(false)
    val otherTurn = MutableStateFlow(false)

    private val payerId = myTurn.combine(otherTurn) { me, other ->
        if (me and other) {
            -1
        } else if (me) {
            balanceDetail.me.participantId
        } else {
            balanceDetail.others[0].participantId
        }
    }

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
