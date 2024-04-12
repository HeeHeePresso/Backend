package org.heeheepresso.orderapi.order.domain.repository

import org.heeheepresso.orderapi.order.domain.model.Order

interface OrderCustomRepository {
    fun findByIdJoinAllMenu(id: Long): Order?
}