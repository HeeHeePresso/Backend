package org.heeheepresso.orderapi.orderHistory.menu.dto.request

import java.math.BigDecimal

data class OrderMenuHistoryCreateRequest(
    val menuId: Long,
    val price: BigDecimal,
    val quantity: Int,
)
