package org.heeheepresso.orderapi.order.domain.repository

import org.heeheepresso.orderapi.order.domain.model.Order
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long>, OrderCustomRepository {

    @EntityGraph(attributePaths = ["orderMenuList"])
    override fun findAll(): MutableList<Order>

}