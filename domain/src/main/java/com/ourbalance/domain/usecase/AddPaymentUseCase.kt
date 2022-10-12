package com.ourbalance.domain.usecase

import com.ourbalance.domain.di.IoDispatcher
import com.ourbalance.domain.model.payment.PaymentInfo
import com.ourbalance.domain.repository.PaymentRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AddPaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<PaymentInfo, Long>(dispatcher) {

    override suspend fun execute(parameters: PaymentInfo): Long {
        return paymentRepository.addPayment(parameters)
    }
}
