package com.ourbalance.data.api

import com.ourbalance.data.entity.payment.PaymentInfoEntity
import com.ourbalance.data.entity.payment.PaymentInfoResponse
import com.ourbalance.data.entity.payment.PaymentListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PaymentService {

    @POST("/payment/create")
    fun addPayment(
        @Body paymentInfo: PaymentInfoEntity
    ): PaymentInfoResponse

    @GET("/payment/{id}")
    fun getAllPaymentsForParticipant(
        @Path("id") balanceId: Long,
        @Query("page") page: Int,
        @Query("pid") pid: Long
    ): PaymentListResponse
}
