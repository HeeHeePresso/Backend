package org.heeheepresso.orderapi.order.dto.request

import org.heeheepresso.orderapi.order.domain.model.OrderMenu
import java.math.BigDecimal

data class OrderMenuCreateRequest(
    val menuId: Long,
    val price: BigDecimal,
    val quantity: Int,
) {
    fun toOrderMenu(): OrderMenu {
        return OrderMenu(
            menuId = menuId,
            price = price,
            quantity = quantity,
        )
    }
}