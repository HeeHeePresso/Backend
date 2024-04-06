package org.heeheepresso.orderapi.orderHistory.domain.model

import jakarta.persistence.*
import org.heeheepresso.orderapi.common.BaseEntity
import org.heeheepresso.orderapi.order.domain.model.OrderStatus
import org.heeheepresso.orderapi.orderHistory.dto.request.OrderHistoryCreateRequest
import org.heeheepresso.orderapi.orderHistory.dto.request.OrderMenuHistoryCreateRequest
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
    var storeId: Long,
    var paymentId: Long,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderHistory", cascade = [CascadeType.ALL], orphanRemoval = true)
    var orderMenuHistoryList: MutableSet<OrderMenuHistory>? = mutableSetOf(),
) : BaseEntity() {
    companion object {
        fun of(request: OrderHistoryCreateRequest): OrderHistory = with(request) {
            val orderHistory = OrderHistory(
                orderId = orderId,
                userId = userId,
                price = price,
                status = status,
                packagedYn = packagedYn,
                storeId = storeId,
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