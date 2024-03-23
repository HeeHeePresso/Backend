package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.*
import org.heeheepresso.orderapi.common.BaseEntity
import java.math.BigDecimal

@Table(name = "order_menu")
@Entity
class OrderMenu (
    menuId: Long,
    menuName: String,
    price: BigDecimal,
    quantity: Int,
    options: List<Option>
) : BaseEntity() {
    fun getTotalAmount(): BigDecimal {
        TODO("Not yet implemented")
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