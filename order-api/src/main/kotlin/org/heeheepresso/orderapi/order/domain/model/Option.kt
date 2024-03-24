package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import org.heeheepresso.orderapi.common.BaseEntity

@Embeddable
data class Option(
    val name: String,
    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "price"))
    val price: Money,
    val quantity: Int,
) : BaseEntity()