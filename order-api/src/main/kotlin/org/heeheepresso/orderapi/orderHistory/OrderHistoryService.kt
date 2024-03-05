package org.heeheepresso.orderapi.orderHistory

import org.heeheepresso.orderapi.orderHistory.dto.request.OrderHistoryCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderHistoryService(
    private val orderHistoryRepository: OrderHistoryRepository,
    private val orderHistoryMapper: OrderHistoryMapper,
) {

    /**
     * 주문 히스토리를 생성합니다.
     *
     * @param request 주문 히스토리 생성 요청
     * @return 생성된 주문 히스토리 Entity
     */
    @Transactional
    fun createOrderHistory(request: OrderHistoryCreateRequest): OrderHistory =
        orderHistoryRepository.save(OrderHistory.of(request))

}