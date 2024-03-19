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

    @OneToMany(
        cascade = [CascadeType.PERSIST, CascadeType.REMOVE],
        orphanRemoval = true
    )
    @JoinColumn(name = "order_id")
    @OrderColumn(name = "list_idx")
    val orderMenuList: List<OrderMenu> = orderMenuList

    @Enumerated(EnumType.STRING)
    var status = OrderStatus.REQUESTED

    var packagedYn = packagedYn

}