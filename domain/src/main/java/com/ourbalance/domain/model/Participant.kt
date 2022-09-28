package com.ourbalance.domain.model

data class Participant(
    val participantId: Long,
    val userName: String,
    val amount: Long,
    val ratio: Int
)
