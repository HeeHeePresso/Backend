package org.heeheepresso.orderapi.order.dto.request

import org.heeheepresso.orderapi.order.domain.model.Order
import java.math.BigDecimal

data class OrderCreateRequest(
    val orderId: Long,
    val userId: Long,
    val amount: BigDecimal,
    val packagedYn: Boolean,
    val storedId: Long,
    val paymentId: Long,
    val orderMenuList: List<OrderMenuCreateRequest>,
) {
    fun toEntity(): Order {
        return Order(
            userId = userId,
            storeId = storedId,
            paymentId = paymentId,
            amount = amount,
            packagedYn = packagedYn,
            orderMenuList = orderMenuList.map(OrderMenuCreateRequest::toOrderMenu)
        )
    }
}
