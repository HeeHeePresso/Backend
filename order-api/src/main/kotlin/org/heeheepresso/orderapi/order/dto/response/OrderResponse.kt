package org.heeheepresso.orderapi.order.dto.response

import org.heeheepresso.orderapi.order.domain.model.Order
import java.math.BigDecimal


data class OrderResponse(
    val orderId: Long,
    val store: String,
    val totalAmount: BigDecimal,
    val menuList: List<OrderMenuResponse>
) {
    companion object {
        fun from(order: Order): OrderResponse {
            return OrderResponse(
                orderId = order.id ?: throw RuntimeException(),
                store = order.store.storeName,
                totalAmount = order.getTotalAmount(),
                menuList = order.orderMenuList.map {
                    OrderMenuResponse.from(it)
                }
            )
        }
    }
    /*
*
* - 스토어 이름
* - 주문상태
* - 메뉴 리스트
*   - 메뉴이름
*   - 메뉴 기본 가격
*   - 옵션 리스트
*   - 최종가격
* */
}
