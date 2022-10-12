package com.ourbalance.data.entity.mapper

import com.ourbalance.data.entity.payment.PaymentEntity
import com.ourbalance.data.entity.payment.PaymentInfoEntity
import com.ourbalance.data.ext.toDateString
import com.ourbalance.domain.model.payment.PaymentInfo
import com.ourbalance.domain.model.payment.PaymentItemModel

fun PaymentInfo.toEntity(): PaymentInfoEntity {
    return PaymentInfoEntity(
        balanceId = balanceId,
        payerId = payerId,
        amount = amount,
        content = content,
        date = date.replace(".", "")
    )
}

fun PaymentEntity.toModel(): PaymentItemModel.Payment {
    return PaymentItemModel.Payment(
        paymentId = paymentId,
        participantId = participantId,
        content = content,
        date = date.toDateString(),
        amount = amount,
        username = username
    )
}
