package org.heeheepresso.orderapi.order.dto.request

import org.heeheepresso.orderapi.order.domain.model.Option

data class OptionCreateRequest(
    val name: String,
    val price: Int,
    val quantity: Int,
) {
    fun toOptionVO(): Option {
        return Option(name, price, quantity)
    }
}
