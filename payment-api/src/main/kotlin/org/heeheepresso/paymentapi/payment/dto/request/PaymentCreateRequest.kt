package org.heeheepresso.paymentapi.payment.dto.request

import java.math.BigDecimal

data class PaymentCreateRequest (
    val orderId: Long,
    val userId: Long,
    val amount: BigDecimal,
    val name: String,
)