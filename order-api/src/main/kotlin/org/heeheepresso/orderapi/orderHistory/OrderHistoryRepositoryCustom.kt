package org.heeheepresso.orderapi.orderHistory

interface OrderHistoryRepositoryCustom {

    fun findDetailById(id: Long): OrderHistory?
}