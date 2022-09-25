package com.ourbalance.data.entity.room

import com.google.gson.annotations.SerializedName

data class RoomInfoResponse(
    @SerializedName("balanaceid")
    val roomId: Long
)
