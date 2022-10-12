package com.ourbalance.data.entity.mapper

import com.ourbalance.data.entity.user.UserInfoEntity
import com.ourbalance.domain.model.auth.UserInfo

fun UserInfoEntity.toModel(): UserInfo {
    return UserInfo(
        email = email,
        password = password,
        userName = userName
    )
}

fun UserInfo.toEntity(): UserInfoEntity {
    return UserInfoEntity(
        email = email,
        password = password,
        userName = userName
    )
}
