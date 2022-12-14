package com.ourbalance.domain.model.room

import java.io.Serializable

data class Participant(
    val participantId: Long,
    val userName: String,
    val amount: Long,
    val ratio: Int
) : Serializable
