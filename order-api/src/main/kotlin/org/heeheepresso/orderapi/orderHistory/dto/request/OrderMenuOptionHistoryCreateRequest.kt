package org.heeheepresso.orderapi.orderHistory.dto.request

import java.math.BigDecimal

data class OrderMenuOptionHistoryCreateRequest(
    val optionId: Long,
    val name: String,
    val price: BigDecimal,
    val quantity: Int,
)
