package org.heeheepresso.orderapi.orderHistory.dto.response

import org.heeheepresso.orderapi.order.OrderStatus
import org.heeheepresso.orderapi.orderHistory.menu.dto.OrderMenuHistoryDto
import java.math.BigDecimal
import java.time.LocalDateTime

data class OrderHistoryCreateResponse(
    val id: Long,
    val orderId: Long,
    val userId: Long,
    val price: BigDecimal,
    val status: OrderStatus,
    val packagedYn: Boolean,
    val storedId: Long,
    val paymentId: Long,
    val orderMenuHistoryList: List<OrderMenuHistoryDto>,

    val createdBy: String,
    val createdDate: LocalDateTime,
    val modifiedBy: String,
    val modifiedDate: LocalDateTime,
)
