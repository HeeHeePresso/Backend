package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.*
import java.math.BigDecimal

@Table(name = "order_menu")
@Entity
class OrderMenu (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val menuId: Long,
    val price: BigDecimal,
    val quantity: Int,
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "order_menu_option",
        joinColumns = [JoinColumn(name = "order_menu_id")]
    )
    val options: List<Option>
)