package com.ourbalance.data.entity.mapper

import com.ourbalance.data.entity.balance.BalanceDetailEntity
import com.ourbalance.data.entity.balance.BalanceInfoEntity
import com.ourbalance.data.entity.balance.ParticipantEntity
import com.ourbalance.data.entity.room.RoomInfoEntity
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceInfo
import com.ourbalance.domain.model.Participant
import com.ourbalance.domain.model.RoomInfo

fun BalanceInfoEntity.toModel(): BalanceInfo {
    return BalanceInfo(
        roomId = roomId,
        roomTitle = roomTitle,
        currentUserCount = currentUserCount,
        maxUserCount = maxUserCount
    )
}

fun BalanceDetailEntity.toModel(): BalanceDetail {
    return BalanceDetail(
        roomId = roomId,
        roomTitle = roomTitle,
        currentUserCount = currentUserCount,
        maxUserCount = maxUserCount,
        password = password,
        participants = participants.map { it.toModel() }
    )
}

fun ParticipantEntity.toModel(): Participant {
    return Participant(
        participantId = participantId,
        userName = userName,
        amount = amount
    )
}

fun RoomInfo.toEntity(): RoomInfoEntity {
    return RoomInfoEntity(
        title = title
    )
}
