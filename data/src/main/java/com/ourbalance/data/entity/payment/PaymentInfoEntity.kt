package com.ourbalance.data.entity.payment

import com.google.gson.annotations.SerializedName

data class PaymentInfoEntity(
    @SerializedName("balanceid")
    val balanceId: Long,
    @SerializedName("participantid")
    val payerId: Long,
    @SerializedName("price")
    val amount: Long,
    @SerializedName("content")
    val content: String,
    @SerializedName("date")
    val date: String
)
