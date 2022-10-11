package com.ourbalance.data.entity.payment

import com.google.gson.annotations.SerializedName

data class PaymentEntity(
    @SerializedName("paymentid")
    val paymentId: Long,
    @SerializedName("participantid")
    val participantId: Long,
    @SerializedName("content")
    val content: String,
    @SerializedName("price")
    val amount: Long,
    @SerializedName("date")
    val date: String,
    @SerializedName("username")
    val username: String
)
