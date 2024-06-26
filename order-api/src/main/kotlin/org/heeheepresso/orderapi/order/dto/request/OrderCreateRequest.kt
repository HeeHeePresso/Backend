package org.heeheepresso.orderapi.order.dto.request

import org.heeheepresso.orderapi.order.domain.model.Order

data class OrderCreateRequest(
    val userId: Long,
    val amount: Int,
    val packagedYn: Boolean,
    val storedId: Long,
    val storeName: String,
    val paymentId: Long,
    val orderMenuList: List<OrderMenuCreateRequest>,
) {
    fun toEntity(): Order {
        return Order(
            userId = userId,
            storeId = storedId,
            storeName = storeName,
            paymentId = paymentId,
            amount = amount,
            packagedYn = packagedYn,
            orderMenuList = orderMenuList.map(OrderMenuCreateRequest::toOrderMenu).toSet()
        )
    }
}
