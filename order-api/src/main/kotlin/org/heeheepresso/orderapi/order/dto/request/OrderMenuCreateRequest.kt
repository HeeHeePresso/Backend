package org.heeheepresso.orderapi.order.dto.request

import org.heeheepresso.orderapi.order.domain.model.Option
import org.heeheepresso.orderapi.order.domain.model.OrderMenu
import java.math.BigDecimal

data class OrderMenuCreateRequest(
    val menuId: Long,
    val menuName: String,
    val price: BigDecimal,
    val quantity: Int,
    //TODO('option 받는 Request 방식 고민하고 변경')
    val options: List<Option>
) {
    fun toOrderMenu(): OrderMenu {
        return OrderMenu(
            menuId = menuId,
            menuName = menuName,
            price = price,
            quantity = quantity,
            options = options,
        )
    }
}