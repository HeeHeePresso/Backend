package org.heeheepresso.orderapi.orderHistory.menu

import com.querydsl.jpa.impl.JPAQueryFactory
import org.heeheepresso.orderapi.orderHistory.OrderHistory
import org.heeheepresso.orderapi.orderHistory.OrderHistoryRepositoryCustom
import org.heeheepresso.orderapi.orderHistory.QOrderHistory.*
import org.heeheepresso.orderapi.orderHistory.menu.QOrderMenuHistory.*
import org.springframework.stereotype.Repository

@Repository
class OrderHistoryRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : OrderHistoryRepositoryCustom {

    override fun findDetailById(id: Long): OrderHistory? = queryFactory
        .selectFrom(orderHistory)
        .leftJoin(orderHistory.orderMenuHistoryList, orderMenuHistory).fetchJoin()
        .where(orderHistory.id.eq(id))
        .fetchOne()
}