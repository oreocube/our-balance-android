package com.ourbalance.data.ext

import com.ourbalance.data.entity.BaseResponse
import com.ourbalance.domain.result.Result

fun <T> getAuthResponse(response: BaseResponse<T>): Result<T> {
    return try {
        if (response.status == 200) {
            Result.Success(response.data!!)
        } else {
            Result.Error(response.error!!)
        }
    } catch (e: Throwable) {
        Result.Error(e.message ?: "Unknown Error")
    }
}
