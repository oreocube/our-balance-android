package com.ourbalance.data.ext

import com.ourbalance.data.entity.BaseResponse
import com.ourbalance.domain.result.Result

fun <T> getResponse(block: BaseResponse<T>): Result<T> {
    return try {
        if (block.status == 200) {
            Result.Success(block.data!!)
        } else {
            Result.Error(block.error!!)
        }
    } catch (e: Throwable) {
        Result.Error(e.message ?: "Unknown Error")
    }
}
