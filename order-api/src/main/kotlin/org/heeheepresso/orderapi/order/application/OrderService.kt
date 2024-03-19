package org.heeheepresso.orderapi.order.application

import org.heeheepresso.orderapi.order.domain.model.Order
import org.heeheepresso.orderapi.order.domain.repository.OrderRepository
import org.heeheepresso.orderapi.order.dto.request.OrderCreateRequest
import org.heeheepresso.orderapi.order.dto.request.OrderUpdateRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {

    @Transactional
    fun createOrder(request: OrderCreateRequest): Order {
        return orderRepository.save(request.toEntity())
    }

    @Transactional
    fun updateOrderStatus(request: OrderUpdateRequest) {
        val order = orderRepository.findByIdOrNull(request.orderId)
            ?: throw RuntimeException()
        order.modifyStatus(request.nextStatus)
    }

}