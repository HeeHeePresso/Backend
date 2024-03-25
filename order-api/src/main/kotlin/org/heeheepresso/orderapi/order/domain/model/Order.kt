package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.*
import org.heeheepresso.orderapi.common.BaseEntity

@Table(name = "orders")
@Entity
class Order(
    userId: Long,
    storeId: Long,
    storeName: String,
    paymentId: Long,
    amount: Int,
    packagedYn: Boolean,
    orderMenuList: List<OrderMenu>
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Embedded
    val buyer: Buyer = Buyer(userId)

    @Embedded
    val store = Store(storeId, storeName)

    @Embedded
    var paymentInfo = PaymentInfo(paymentId, Money(amount))

    @OneToMany(
        cascade = [CascadeType.PERSIST, CascadeType.REMOVE],
        orphanRemoval = true)
    @JoinColumn(name = "order_id")
    val orderMenuList: List<OrderMenu> = orderMenuList

    @Enumerated(EnumType.STRING)
    var status = OrderStatus.REQUESTED

    var packagedYn = packagedYn

    fun modifyStatus(nextStatus: OrderStatus) {
        status.checkCanChangeable(nextStatus)
        status = nextStatus
    }

    fun getTotalAmount(): Money {
        return paymentInfo.amount
    }
}