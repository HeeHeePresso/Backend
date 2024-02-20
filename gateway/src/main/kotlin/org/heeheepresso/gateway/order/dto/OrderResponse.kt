package org.heeheepresso.gateway.order.dto

data class OrderResponse(
    val userId: String,
    val orders: List<Order>
)
