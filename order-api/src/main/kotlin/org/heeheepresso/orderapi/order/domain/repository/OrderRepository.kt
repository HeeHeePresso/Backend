package org.heeheepresso.orderapi.order.domain.repository

import org.heeheepresso.orderapi.order.domain.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}