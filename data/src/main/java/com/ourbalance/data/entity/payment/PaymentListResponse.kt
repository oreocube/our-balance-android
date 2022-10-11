package com.ourbalance.data.entity.payment

import com.google.gson.annotations.SerializedName
import com.ourbalance.data.entity.balance.ParticipantEntity

data class PaymentListResponse(
    @SerializedName("listCount")
    val listCount: Long,
    @SerializedName("userList")
    val users: List<ParticipantEntity>,
    @SerializedName("paymentList")
    val payments: List<PaymentEntity>
)
