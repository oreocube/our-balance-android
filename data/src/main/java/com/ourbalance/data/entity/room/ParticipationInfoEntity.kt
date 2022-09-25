package com.ourbalance.data.entity.room

import com.google.gson.annotations.SerializedName

data class ParticipationInfoEntity(
    @SerializedName("balanceid")
    val roomId: Long,
    @SerializedName("password")
    val password: String
)
