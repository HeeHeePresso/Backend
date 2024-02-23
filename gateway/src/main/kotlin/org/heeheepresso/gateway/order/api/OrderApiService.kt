package org.heeheepresso.gateway.order.api

import com.google.common.collect.Lists
import org.heeheepresso.gateway.user.UserRole.CUSTOMER
import org.heeheepresso.gateway.user.UserRole.EMPLOYEE

class OrderApiService {
    fun getOrders(orderApiRequest: OrderApiRequest): OrderApiResponse {
        return when (orderApiRequest.userRole) {
            EMPLOYEE -> {
                // TODO : implement calling orders by employee
                OrderApiResponse(
                    userId = orderApiRequest.userId,
                    orders = Lists.newArrayList()
                )
            }
            CUSTOMER -> {
                // TODO : implement calling orders by customer
                OrderApiResponse(
                    userId = orderApiRequest.userId,
                    orders = Lists.newArrayList()
                )
            }
        }
    }
}