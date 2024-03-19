package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
data class Option(
    val name: String,
    val price: BigDecimal,
    val quantity: Int,
)