package org.heeheepresso.orderapi.orderHistory.domain.repository

import org.heeheepresso.orderapi.orderHistory.domain.model.OrderHistory

interface OrderHistoryRepositoryCustom {

    fun findDetailById(id: Long): OrderHistory?
}