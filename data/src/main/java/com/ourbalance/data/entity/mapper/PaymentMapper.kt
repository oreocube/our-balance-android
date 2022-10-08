package com.ourbalance.data.entity.mapper

import com.ourbalance.data.entity.payment.PaymentInfoEntity
import com.ourbalance.domain.model.PaymentInfo

fun PaymentInfo.toEntity(): PaymentInfoEntity {
    return PaymentInfoEntity(
        balanceId = balanceId,
        payerId = payerId,
        amount = amount,
        content = content,
        date = date
    )
}
