package org.heeheepresso.orderapi.orderHistory.dto.request

import org.heeheepresso.orderapi.order.domain.model.OrderStatus
import java.math.BigDecimal

data class OrderHistoryCreateRequest(
    val orderId: Long,
    val userId: Long,
    val price: BigDecimal,
    val status: OrderStatus,
    val packagedYn: Boolean,
    val storeId: Long,
    val paymentId: Long,
    val orderMenuHistoryList: List<OrderMenuHistoryCreateRequest>,
)
