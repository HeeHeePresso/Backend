package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded

@Embeddable
data class MenuInfo(
    val menuId: Long,
    val menuName: String,
    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "price"))
    val price: Money,
) {
    constructor(menuId: Long, menuName: String, price: Int): this(menuId, menuName, Money(price))

}
