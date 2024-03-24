package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.*

@Embeddable
data class MenuInfo(
    val menuId: Long,
    val menuName: String,
    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "price"))
    val price: Money,
)
