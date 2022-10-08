package com.ourbalance.data.api

import com.ourbalance.data.entity.payment.PaymentInfoEntity
import com.ourbalance.data.entity.payment.PaymentInfoResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentService {

    @POST("/payment/create")
    fun addPayment(
        @Body paymentInfo: PaymentInfoEntity
    ): PaymentInfoResponse
}
