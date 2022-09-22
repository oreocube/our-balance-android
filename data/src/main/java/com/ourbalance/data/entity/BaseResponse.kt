package com.ourbalance.data.entity

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("error")
    val error: String?,
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: T?
)
