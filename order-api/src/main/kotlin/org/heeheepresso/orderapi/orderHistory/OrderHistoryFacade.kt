package org.heeheepresso.orderapi.orderHistory

import org.heeheepresso.orderapi.orderHistory.dto.request.OrderHistoryCreateRequest
import org.heeheepresso.orderapi.orderHistory.dto.response.OrderHistoryCreateResponse
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class OrderHistoryFacade(
    private val orderHistoryService: OrderHistoryService,
    private val orderHistoryMapper: OrderHistoryMapper,
) {

    @Transactional
    fun createOrderHistory(request: OrderHistoryCreateRequest): OrderHistoryCreateResponse =
        orderHistoryMapper.convertToResponse(orderHistoryService.createOrderHistory(request))

}