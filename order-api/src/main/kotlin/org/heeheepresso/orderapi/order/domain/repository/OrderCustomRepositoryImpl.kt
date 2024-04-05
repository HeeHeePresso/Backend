package org.heeheepresso.orderapi.order.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.heeheepresso.orderapi.order.domain.model.Order
import org.heeheepresso.orderapi.order.domain.model.QOption.option
import org.heeheepresso.orderapi.order.domain.model.QOrder.*
import org.heeheepresso.orderapi.order.domain.model.QOrderMenu.orderMenu
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
class OrderCustomRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
): OrderCustomRepository {
    @Query("""
        SELECT o FROM Order o
        JOIN FETCH o.orderMenuList om
        LEFT JOIN FETCH om.options
        WHERE o.id = :id
        ORDER BY om.id ASC
    """)
    override fun findByIdJoinAllMenu(id: Long): Order? {
        val query = jpaQueryFactory
            .selectFrom(order)
            .join(order.orderMenuList, orderMenu).fetchJoin()
            .join(orderMenu.options, option).fetchJoin()
            .where(order.id.eq(id))
            .orderBy(orderMenu.id.asc())
        return query.fetchOne()
    }

}