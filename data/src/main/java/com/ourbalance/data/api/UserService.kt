package com.ourbalance.data.api

import com.ourbalance.data.entity.BaseResponse
import retrofit2.http.GET

interface UserService {
    @GET("/mypage")
    suspend fun getUser(): BaseResponse<String>
}
