package com.ourbalance.data.entity.balance

import com.google.gson.annotations.SerializedName

data class ParticipantEntity(
    @SerializedName("participantid")
    val participantId: Long,
    @SerializedName("username")
    val userName: String,
    @SerializedName("paymentsum")
    val amount: Long
)
