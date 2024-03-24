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
    val options: List<Option>,
    //TODO 이 값을 받을 것인지 논의 필요(프론트 or 메뉴쪽에서 계산??)
    val totalAmount: BigDecimal,
) {

    //TODO 생성 시 totalAmount 검증로직 (기본가격 + options의 가격 과 동일한지)
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