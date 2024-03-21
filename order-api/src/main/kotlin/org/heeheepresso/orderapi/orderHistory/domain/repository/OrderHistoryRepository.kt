package org.heeheepresso.orderapi.orderHistory.domain.repository

import org.heeheepresso.orderapi.orderHistory.domain.model.OrderHistory
import org.springframework.data.jpa.repository.JpaRepository

interface OrderHistoryRepository : JpaRepository<OrderHistory, Long>, OrderHistoryRepositoryCustom