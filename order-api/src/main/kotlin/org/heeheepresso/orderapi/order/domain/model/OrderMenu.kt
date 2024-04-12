package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.*
import org.heeheepresso.orderapi.common.BaseEntity

@Table(name = "order_menu")
@Entity
class OrderMenu (
    menuId: Long,
    menuName: String,
    price: Int,
    quantity: Int,
    options: List<Option>,
    totalAmount: Int
) : BaseEntity() {

    init {
        val totalValue = price + options.sumOf { it.price.getIntValue() * it.quantity }
        if (totalValue != totalAmount) {
            throw RuntimeException()
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Embedded
    val menuInfo = MenuInfo(menuId, menuName, price)

    val quantity = quantity

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "order_menu_option",
        joinColumns = [JoinColumn(name = "order_menu_id")]
    )
    val options = options

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "totalPrice"))
    val totalAmount = Money(totalAmount)

}
