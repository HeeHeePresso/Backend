package org.heeheepresso.orderapi.orderHistory.menu

import jakarta.persistence.*
import org.heeheepresso.orderapi.common.BaseEntity
import org.heeheepresso.orderapi.orderHistory.OrderHistory
import org.heeheepresso.orderapi.orderHistory.menu.dto.request.OrderMenuHistoryCreateRequest
import java.math.BigDecimal

@Entity
@Table(name = "order_menu_history")
class OrderMenuHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var menuId: Long,
    var price: BigDecimal,
    var quantity: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_history_id")
    var orderHistory: OrderHistory? = null,
) : BaseEntity() {
    companion object {
        fun of(orderHistory: OrderHistory, request: OrderMenuHistoryCreateRequest): OrderMenuHistory =
            OrderMenuHistory(
                menuId = request.menuId,
                price = request.price,
                quantity = request.quantity,
                orderHistory = orderHistory,
            )
    }
}