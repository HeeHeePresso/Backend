package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
data class MenuInfo(
    val menuId: Long,
    val menuName: String,
    val price: BigDecimal,
)
