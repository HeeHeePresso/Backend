package org.heeheepresso.orderapi.orderHistory.dto.request

import org.heeheepresso.orderapi.order.OrderStatus
import org.heeheepresso.orderapi.orderHistory.menu.dto.request.OrderMenuHistoryCreateRequest
import java.math.BigDecimal

data class OrderHistoryCreateRequest(
    val orderId: Long,
    val userId: Long,
    val price: BigDecimal,
    val status: OrderStatus,
    val packagedYn: Boolean,
    val storedId: Long,
    val paymentId: Long,
    val orderMenuHistoryList: List<OrderMenuHistoryCreateRequest>,
)
