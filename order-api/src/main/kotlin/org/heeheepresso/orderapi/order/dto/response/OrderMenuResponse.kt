package org.heeheepresso.orderapi.order.dto.response

import org.heeheepresso.orderapi.order.domain.model.OrderMenu

data class OrderMenuResponse(
    val name: String,
    val price: Int,
    val options: List<OrderMenuOptionResponse>,
    val totalAmount: Int,
) {
    companion object {
        fun from(orderMenu: OrderMenu): OrderMenuResponse {
            return OrderMenuResponse(
                name = orderMenu.menuInfo.menuName,
                price = orderMenu.menuInfo.price.getIntValue(),
                options = orderMenu.options.map {
                    OrderMenuOptionResponse.from(it)
                },
                totalAmount = orderMenu.totalAmount.getIntValue()
            )
        }
    }
}
