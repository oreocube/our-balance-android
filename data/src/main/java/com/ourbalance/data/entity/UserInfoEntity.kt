package com.ourbalance.data.entity

data class UserInfoEntity(
    val email: String,
    val password: String,
    val userName: String? = null
)
