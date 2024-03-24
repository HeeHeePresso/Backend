package org.heeheepresso.orderapi.order.dto.request

import org.heeheepresso.orderapi.order.domain.model.Money
import org.heeheepresso.orderapi.order.domain.model.Option
import org.heeheepresso.orderapi.order.domain.model.OrderMenu

data class OrderMenuCreateRequest(
    val menuId: Long,
    val menuName: String,
    val price: Int,
    val quantity: Int,
    //TODO('option 받는 Request 방식 고민하고 변경')
    val options: List<Option>,
    //TODO 이 값을 받을 것인지 논의 필요(프론트 or 메뉴쪽에서 계산??)
    val totalAmount: Int,
) {

    //TODO 생성 시 totalAmount 검증로직 (기본가격 + options의 가격 과 동일한지)
    fun toOrderMenu(): OrderMenu {
        return OrderMenu(
            menuId = menuId,
            menuName = menuName,
            price = Money(price),
            quantity = quantity,
            options = options,
        )
    }
}