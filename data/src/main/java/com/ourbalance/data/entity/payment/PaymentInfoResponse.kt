package com.ourbalance.data.entity.payment

import com.google.gson.annotations.SerializedName

data class PaymentInfoResponse(
    @SerializedName("paymentid")
    val paymentId: Long
)
