package com.ourbalance.data.entity.user

data class UserInfoEntity(
    val email: String,
    val password: String,
    val userName: String? = null
)
