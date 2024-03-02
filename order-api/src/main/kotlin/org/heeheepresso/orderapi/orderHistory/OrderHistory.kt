package org.heeheepresso.orderapi.orderHistory

import jakarta.persistence.*
import org.heeheepresso.orderapi.common.BaseEntity
import org.heeheepresso.orderapi.order.OrderStatus
import org.heeheepresso.orderapi.orderHistory.dto.request.OrderHistoryCreateRequest
import org.heeheepresso.orderapi.orderHistory.menu.OrderMenuHistory
import org.heeheepresso.orderapi.orderHistory.menu.dto.request.OrderMenuHistoryCreateRequest
import java.math.BigDecimal

@Entity
@Table(name = "order_history")
class OrderHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var orderId: Long,
    var userId: Long,
    var price: BigDecimal,
    @Enumerated(value = EnumType.STRING)
    var status: OrderStatus,
    var packagedYn: Boolean,
    var storedId: Long,
    var paymentId: Long,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderHistory", cascade = [CascadeType.ALL], orphanRemoval = true)
    var orderMenuHistoryList: MutableList<OrderMenuHistory>? = mutableListOf(),
) : BaseEntity() {
    companion object {
        fun of(request: OrderHistoryCreateRequest): OrderHistory = with(request) {
            val orderHistory = OrderHistory(
                orderId = orderId,
                userId = userId,
                price = price,
                status = status,
                packagedYn = packagedYn,
                storedId = storedId,
                paymentId = paymentId,
            )
            orderHistory.addAllOrderMenuHistory(orderMenuHistoryList)
            orderHistory
        }
    }

    private fun addAllOrderMenuHistory(orderMenuHistoryList: List<OrderMenuHistoryCreateRequest>) {
        orderMenuHistoryList.map { OrderMenuHistory.of(this, it) }.let { this.orderMenuHistoryList?.addAll(it) }
    }
}