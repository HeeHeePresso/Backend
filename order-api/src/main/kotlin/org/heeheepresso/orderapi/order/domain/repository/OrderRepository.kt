package org.heeheepresso.orderapi.order.domain.repository

import org.heeheepresso.orderapi.order.domain.model.Order
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface OrderRepository : JpaRepository<Order, Long> {

    @Query("""
        SELECT o FROM Order o
        JOIN FETCH o.orderMenuList om
        LEFT JOIN FETCH om.options
        WHERE o.id = :id
    """)
    fun findByIdJoinAllMenu(id: Long): Order?

    @EntityGraph(attributePaths = ["orderMenuList"])
    override fun findAll(): MutableList<Order>

}