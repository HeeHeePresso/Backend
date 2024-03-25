package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded

@Embeddable
data class PaymentInfo(
    val paymentId: Long,
    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "amount"))
    val amount: Money
)
