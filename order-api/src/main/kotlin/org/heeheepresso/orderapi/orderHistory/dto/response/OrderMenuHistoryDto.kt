package org.heeheepresso.orderapi.orderHistory.dto.response

import java.math.BigDecimal

data class OrderMenuHistoryDto(
    val id: Long,
    val orderHistoryId: Long,
    val menuId: Long,
    val price: BigDecimal,
    val quantity: Int,
)
