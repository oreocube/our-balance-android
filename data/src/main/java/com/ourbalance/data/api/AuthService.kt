package com.ourbalance.data.api

import com.ourbalance.data.entity.BaseResponse
import com.ourbalance.data.entity.user.UserInfoEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/user/signup")
    suspend fun signup(@Body userInfoEntity: UserInfoEntity): BaseResponse<Unit>

    @POST("/user/login")
    suspend fun login(@Body userInfoEntity: UserInfoEntity): BaseResponse<String>
}
