package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.*
import org.heeheepresso.orderapi.common.BaseEntity

@Table(name = "order_menu")
@Entity
class OrderMenu (
    menuId: Long,
    menuName: String,
    price: Money,
    quantity: Int,
    options: List<Option>
) : BaseEntity() {
    fun getTotalAmount(): Int {
        return menuInfo.price.getIntValue() + options.sumOf { it.price.getIntValue() * it.quantity }
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
}
