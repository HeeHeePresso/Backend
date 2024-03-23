package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.Embeddable

@Embeddable
data class Store(
    val storeId: Long,
    val storeName: String,
)
