package org.heeheepresso.orderapi.order.dto.response

import org.heeheepresso.orderapi.order.domain.model.Option

data class OrderMenuOptionResponse(
    val name: String,
    val price: Int,
    val quantity: Int,
) {
    companion object {
        fun from(option: Option): OrderMenuOptionResponse {
            return OrderMenuOptionResponse(
                name = option.name,
                price = option.price.getIntValue(),
                quantity = option.quantity
            )
        }
    }
}
