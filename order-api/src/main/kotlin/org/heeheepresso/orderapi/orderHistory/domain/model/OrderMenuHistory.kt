package org.heeheepresso.orderapi.orderHistory.domain.model

import jakarta.persistence.*
import org.heeheepresso.orderapi.common.BaseEntity
import org.heeheepresso.orderapi.orderHistory.dto.request.OrderMenuHistoryCreateRequest
import org.heeheepresso.orderapi.orderHistory.dto.request.OrderMenuOptionHistoryCreateRequest
import java.math.BigDecimal

@Entity
@Table(name = "order_menu_history")
class OrderMenuHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    var menuId: Long,
    var price: BigDecimal,
    var quantity: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_history_id")
    var orderHistory: OrderHistory? = null,

    @OrderBy("id")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderMenuHistory", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orderMenuOptionHistoryList: MutableSet<OrderMenuOptionHistory>? = LinkedHashSet(),
) : BaseEntity() {
    companion object {
        fun of(orderHistory: OrderHistory, request: OrderMenuHistoryCreateRequest): OrderMenuHistory {
            val orderMenuHistory = OrderMenuHistory(
                menuId = request.menuId,
                name = request.name,
                price = request.price,
                quantity = request.quantity,
                orderHistory = orderHistory,
            )

            orderMenuHistory.addAllOrderMenuOptionHistory(request.orderMenuOptionHistoryCreateRequest)
            return orderMenuHistory
        }
    }

    private fun addAllOrderMenuOptionHistory(orderMenuOptionHistoryList: List<OrderMenuOptionHistoryCreateRequest>) {
        orderMenuOptionHistoryList.map { OrderMenuOptionHistory.of(this, it) }
            .let { this.orderMenuOptionHistoryList?.addAll(it) }
    }
}