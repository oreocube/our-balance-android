package com.ourbalance.data.entity.mapper

import com.ourbalance.data.entity.room.ParticipationInfoEntity
import com.ourbalance.data.entity.room.RoomInfoEntity
import com.ourbalance.domain.model.room.ParticipationInfo
import com.ourbalance.domain.model.room.RoomInfo

fun RoomInfo.toEntity(): RoomInfoEntity {
    return RoomInfoEntity(
        title = title
    )
}

fun ParticipationInfo.toEntity(): ParticipationInfoEntity {
    return ParticipationInfoEntity(
        roomId = roomId,
        password = password
    )
}
