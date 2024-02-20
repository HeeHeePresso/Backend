package org.heeheepresso.gateway.order

import mu.KotlinLogging
import org.heeheepresso.gateway.order.dto.OrderResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping
    suspend fun getOrders(@RequestParam("userId") userId : String): Mono<OrderResponse> {
        return orderService.getOrders(userId)
            .onErrorResume {  e ->
                logger.error("menu detail error: ${e.message}" + e)
                Mono.error(e)
            }
    }
}