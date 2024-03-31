package org.heeheepresso.orderapi.orderHistory.dto.request

import java.math.BigDecimal

data class OrderMenuHistoryCreateRequest(
    val menuId: Long,
    val name: String,
    val price: BigDecimal,
    val quantity: Int,
    val orderMenuOptionHistoryCreateRequest: List<OrderMenuOptionHistoryCreateRequest>,
)
