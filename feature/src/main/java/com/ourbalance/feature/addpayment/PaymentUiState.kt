package com.ourbalance.feature.addpayment

sealed class PaymentUiState() {
    object UnInitialized : PaymentUiState()
    object PayerSelected : PaymentUiState()
    object AmountSpecified : PaymentUiState()
    object ContentSpecified : PaymentUiState()
    object Completed : PaymentUiState()

    fun onNext(): PaymentUiState {
        return when (this) {
            is UnInitialized -> PayerSelected
            is PayerSelected -> AmountSpecified
            is AmountSpecified -> ContentSpecified
            is ContentSpecified -> Completed
            is Completed -> Completed
        }
    }

    fun onPrev(): PaymentUiState {
        return when (this) {
            is UnInitialized -> UnInitialized
            is PayerSelected -> UnInitialized
            is AmountSpecified -> PayerSelected
            is ContentSpecified -> AmountSpecified
            is Completed -> ContentSpecified
        }
    }
}
