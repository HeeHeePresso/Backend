package org.heeheepresso.orderapi.order.dto.request

import org.heeheepresso.orderapi.order.domain.model.OrderMenu

data class OrderMenuCreateRequest(
    val menuId: Long,
    val menuName: String,
    val price: Int,
    val quantity: Int,
    val options: List<OptionCreateRequest>,
    val totalAmount: Int,
) {

    //TODO 생성 시 totalAmount 검증로직 (기본가격 + options의 가격 과 동일한지)
    fun toOrderMenu(): OrderMenu {
        return OrderMenu(
            menuId = menuId,
            menuName = menuName,
            price = price,
            quantity = quantity,
            options = options.map(OptionCreateRequest::toOptionVO),
            totalAmount = totalAmount,
        )
    }
}