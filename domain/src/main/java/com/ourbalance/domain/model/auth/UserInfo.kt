package com.ourbalance.domain.model.auth

data class UserInfo(
    val email: String,
    val password: String,
    val userName: String? = null
)
