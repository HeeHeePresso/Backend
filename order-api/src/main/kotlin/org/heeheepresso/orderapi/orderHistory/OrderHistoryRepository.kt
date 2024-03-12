package org.heeheepresso.orderapi.orderHistory

import org.springframework.data.jpa.repository.JpaRepository

interface OrderHistoryRepository : JpaRepository<OrderHistory, Long>, OrderHistoryRepositoryCustom