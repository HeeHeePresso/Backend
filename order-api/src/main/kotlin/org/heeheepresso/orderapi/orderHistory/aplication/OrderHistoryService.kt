package org.heeheepresso.orderapi.orderHistory.aplication

import org.heeheepresso.orderapi.common.ApiException
import org.heeheepresso.orderapi.common.ApiStatus
import org.heeheepresso.orderapi.orderHistory.domain.model.OrderHistory
import org.heeheepresso.orderapi.orderHistory.domain.repository.OrderHistoryRepository
import org.heeheepresso.orderapi.orderHistory.dto.request.OrderHistoryCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
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

    /**
     * 주문 히스토리를 조회합니다.
     *
     * @param id 주문 히스토리 ID
     * @return 생성된 주문 히스토리 Entity
     */
    fun getOrderHistoryById(id: Long): OrderHistory {
        return orderHistoryRepository.findDetailById(id) ?: throw ApiException(ApiStatus.NOT_EXISTS_ORDER_HISTORY)
    }

}