package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.Embeddable

@Embeddable
data class Buyer(
    val userId: Long
)
