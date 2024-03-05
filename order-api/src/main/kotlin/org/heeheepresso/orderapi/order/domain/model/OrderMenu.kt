package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
data class OrderMenu (
    val menuId: Long,
    val price: BigDecimal,
    val quantity: Int,
)
