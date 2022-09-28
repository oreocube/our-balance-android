package com.ourbalance.data.entity.mapper

import com.ourbalance.data.entity.balance.BalanceDetailEntity
import com.ourbalance.data.entity.balance.BalanceInfoEntity
import com.ourbalance.data.entity.balance.ParticipantEntity
import com.ourbalance.data.ext.getRatio
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.model.Participant

fun BalanceInfoEntity.toModel(): BalanceInfo {
    return BalanceInfo(
        roomId = roomId,
        roomTitle = roomTitle,
        currentUserCount = currentUserCount,
        maxUserCount = maxUserCount
    )
}

fun BalanceDetailEntity.toModel(userId: Long): BalanceDetail {
    val group = participants.map { it.toModel(total) }.groupBy { it.participantId == userId }

    return BalanceDetail(
        roomId = roomId,
        roomTitle = roomTitle,
        isFull = currentUserCount == maxUserCount,
        password = password,
        total = total,
        me = group[true]!![0],
        others = group[false] ?: listOf()
    )
}

fun ParticipantEntity.toModel(total: Long): Participant {
    return Participant(
        participantId = participantId,
        userName = userName,
        amount = amount,
        ratio = amount.getRatio(total)
    )
}
