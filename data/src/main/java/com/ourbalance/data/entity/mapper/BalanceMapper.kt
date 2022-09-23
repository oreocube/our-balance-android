package com.ourbalance.data.entity.mapper

import com.ourbalance.data.entity.balance.BalanceInfoEntity
import com.ourbalance.domain.model.BalanceInfo

fun BalanceInfoEntity.toModel(): BalanceInfo {
    return BalanceInfo(
        roomId = roomId,
        roomTitle = roomTitle,
        currentUserCount = currentUserCount,
        maxUserCount = maxUserCount
    )
}
