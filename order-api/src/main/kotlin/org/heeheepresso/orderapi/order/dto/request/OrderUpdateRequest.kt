package org.heeheepresso.orderapi.order.dto.request

import org.heeheepresso.orderapi.order.domain.model.OrderStatus

data class OrderUpdateRequest(
    val orderId: Long,
    val nextStatus: OrderStatus,
)