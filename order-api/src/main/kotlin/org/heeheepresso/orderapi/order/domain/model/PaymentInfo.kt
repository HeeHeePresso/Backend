package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
data class PaymentInfo(
    val paymentId: Long,
    val amount: BigDecimal
)
