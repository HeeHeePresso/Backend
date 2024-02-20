package org.heeheepresso.gateway.order.api

import org.heeheepresso.gateway.order.dto.Order

data class OrderApiResponse(
    val userId: String,
    val orders: List<Order>
)
