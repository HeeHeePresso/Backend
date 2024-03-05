package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.*
import org.heeheepresso.orderapi.common.BaseEntity
import java.math.BigDecimal

@Table(name = "orders")
@Entity
class Order(
    userId: Long,
    storeId: Long,
    paymentId: Long,
    amount: BigDecimal,
    packagedYn: Boolean,
    orderMenuList: List<OrderMenu>
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Embedded
    val buyer: Buyer = Buyer(userId)

    @Embedded
    val store = Store(storeId)

    @Embedded
    var paymentInfo = PaymentInfo(paymentId, amount)

    @Enumerated(EnumType.STRING)
    var status = OrderStatus.REQUESTED

    var packagedYn = packagedYn

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_menu", joinColumns = [JoinColumn(name = "order_id")])
    @OrderColumn(name = "line_idx")
    val orderMenuList: List<OrderMenu> = orderMenuList

}