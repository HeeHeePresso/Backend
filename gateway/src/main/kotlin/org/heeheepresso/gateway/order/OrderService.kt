package org.heeheepresso.gateway.order

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.mono
import org.heeheepresso.gateway.order.api.OrderApiRequest
import org.heeheepresso.gateway.order.api.OrderApiService
import org.heeheepresso.gateway.order.dto.OrderResponse
import org.heeheepresso.gateway.user.UserService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrderService(
    private val userService: UserService,
    private val orderApiService: OrderApiService,
) {
    suspend fun getOrders(userId: String): Mono<OrderResponse> = coroutineScope {
        mono { userService.getUserRole(userId) }
            .map {  OrderApiRequest(
               userId = userId,
               userRole = it,
            )}
            .map { orderApiService.getOrders(it) }
            .map { OrderResponse(
                userId = it.userId,
                orders = it.orders,
            )}
    }
}