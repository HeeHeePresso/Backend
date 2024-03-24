package org.heeheepresso.orderapi.order.dto.response

import org.heeheepresso.orderapi.order.domain.model.Option
import java.math.BigDecimal

data class OrderMenuOptionResponse(
    val name: String,
    val price: BigDecimal,
    val quantity: Int,
) {
    companion object {
        fun from(option: Option): OrderMenuOptionResponse {
            return OrderMenuOptionResponse(
                name = option.name,
                price = option.price,
                quantity = option.quantity
            )
        }
    }
}
