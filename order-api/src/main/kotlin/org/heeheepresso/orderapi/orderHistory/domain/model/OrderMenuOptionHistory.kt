package org.heeheepresso.orderapi.orderHistory.domain.model

import jakarta.persistence.*
import org.heeheepresso.orderapi.common.BaseEntity
import org.heeheepresso.orderapi.orderHistory.dto.request.OrderMenuOptionHistoryCreateRequest
import java.math.BigDecimal

@Entity
@Table(name = "order_menu_option_history")
class OrderMenuOptionHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val optionId: Long,
    val name: String,
    val price: BigDecimal,
    val quantity: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_menu_history_id")
    val orderMenuHistory: OrderMenuHistory? = null,

    ) : BaseEntity() {
    companion object {
        fun of(orderMenuHistory: OrderMenuHistory, request: OrderMenuOptionHistoryCreateRequest): OrderMenuOptionHistory =
            OrderMenuOptionHistory(
                optionId = request.optionId,
                name = request.name,
                price = request.price,
                quantity = request.quantity,
                orderMenuHistory = orderMenuHistory,
            )
    }
}