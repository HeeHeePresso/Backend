package org.heeheepresso.orderapi.orderHistory.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.heeheepresso.orderapi.orderHistory.domain.model.OrderHistory
import org.heeheepresso.orderapi.orderHistory.domain.model.QOrderHistory.orderHistory
import org.heeheepresso.orderapi.orderHistory.domain.model.QOrderMenuHistory.orderMenuHistory
import org.heeheepresso.orderapi.orderHistory.domain.model.QOrderMenuOptionHistory.orderMenuOptionHistory
import org.springframework.stereotype.Repository

@Repository
class OrderHistoryRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : OrderHistoryRepositoryCustom {

    override fun findDetailById(id: Long): OrderHistory? {
        val orderHistory = queryFactory
            .selectFrom(orderHistory)
            .leftJoin(orderHistory.orderMenuHistoryList, orderMenuHistory).fetchJoin()
            .leftJoin(orderMenuHistory.orderMenuOptionHistoryList, orderMenuOptionHistory).fetchJoin()
            .where(orderHistory.id.eq(id))
            .fetchOne()

        orderHistory?.orderMenuHistoryList?.sortedBy { it.id }
        orderHistory?.orderMenuHistoryList?.forEach { orderMenuHistory ->
            run {
                orderMenuHistory.orderMenuOptionHistoryList?.sortedBy { it.id }
            }
        }

        return orderHistory;
    }
}