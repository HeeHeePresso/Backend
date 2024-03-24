package org.heeheepresso.orderapi.order.dto.response

import org.heeheepresso.orderapi.order.domain.model.OrderMenu
import java.math.BigDecimal

data class OrderMenuResponse(
    val name: String,
    val price: BigDecimal,
    val options: List<OrderMenuOptionResponse>,
    val totalAmount: BigDecimal,
) {
    companion object {
        fun from(orderMenu: OrderMenu): OrderMenuResponse {
            return OrderMenuResponse(
                name = orderMenu.menuInfo.menuName,
                price = orderMenu.menuInfo.price,
                options = orderMenu.options.map {
                    OrderMenuOptionResponse.from(it)
                },
                totalAmount = orderMenu.getTotalAmount()
            )
        }
    }
}
