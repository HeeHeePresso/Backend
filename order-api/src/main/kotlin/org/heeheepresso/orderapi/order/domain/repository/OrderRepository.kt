package org.heeheepresso.orderapi.order.domain.repository

import org.heeheepresso.orderapi.order.domain.model.Order
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OrderRepository : JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = ["orderMenuList"])
    override fun findById(id: Long): Optional<Order>

    @EntityGraph(attributePaths = ["orderMenuList"])
    override fun findAll(): MutableList<Order>

}