package org.heeheepresso.orderapi.orderHistory

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.heeheepresso.orderapi.order.OrderStatus
import org.heeheepresso.orderapi.orderHistory.dto.request.OrderHistoryCreateRequest
import org.heeheepresso.orderapi.orderHistory.menu.dto.request.OrderMenuHistoryCreateRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

@SpringBootTest
class OrderHistoryServiceTest @Autowired constructor(
    private val orderHistoryService: OrderHistoryService
) : BehaviorSpec() {

    init {
        Given("주문 히스토리 서비스 테스트") {
            When("주문 히스토리를 저장하면") {
                val request = OrderHistoryCreateRequest(
                    orderId = 1,
                    userId = 1,
                    price = BigDecimal.ZERO,
                    status = OrderStatus.WAITING,
                    packagedYn = false,
                    storedId = 1,
                    paymentId = 1,
                    orderMenuHistoryList = emptyList(),
                )

                val result = orderHistoryService.createOrderHistory(request)

                Then("저장한 주문 히스토리 그대로를 반환하고 주문 메뉴 리스트는 null 반환 (주문 메뉴는 별도의 서비스에서 처리)") {
                    result.orderId shouldBe request.orderId
                    result.userId shouldBe request.userId
                    result.price shouldBe request.price
                    result.status shouldBe request.status
                    result.packagedYn shouldBe request.packagedYn
                    result.storedId shouldBe request.storedId
                    result.paymentId shouldBe request.paymentId
                    result.orderMenuHistoryList?.size shouldBe 0

                }
            }

            When("주문 메뉴와 함께 히스토리를 저장하면") {
                val orderMenuHistory = listOf(
                    OrderMenuHistoryCreateRequest(menuId = 1, price = BigDecimal.ZERO, quantity = 1),
                    OrderMenuHistoryCreateRequest(menuId = 2, price = BigDecimal.ZERO, quantity = 2)
                )

                val request = OrderHistoryCreateRequest(
                    orderId = 1,
                    userId = 1,
                    price = BigDecimal.ZERO,
                    status = OrderStatus.WAITING,
                    packagedYn = false,
                    storedId = 1,
                    paymentId = 1,
                    orderMenuHistoryList = orderMenuHistory,
                )

                val result = orderHistoryService.createOrderHistory(request)

                Then("저장한 주문 및 주문 메뉴 히스토리 그대로 반환") {
                    result.orderId shouldBe request.orderId
                    result.userId shouldBe request.userId
                    result.price shouldBe request.price
                    result.status shouldBe request.status
                    result.packagedYn shouldBe request.packagedYn
                    result.storedId shouldBe request.storedId
                    result.paymentId shouldBe request.paymentId
                    result.orderMenuHistoryList?.size shouldBe 2
                    request.orderMenuHistoryList.forEachIndexed { index, orderMenuHistory ->
                        orderMenuHistory.menuId shouldBe request.orderMenuHistoryList[index].menuId
                        orderMenuHistory.price shouldBe request.orderMenuHistoryList[index].price
                        orderMenuHistory.quantity shouldBe request.orderMenuHistoryList[index].quantity

                    }
                }
            }
        }
    }
}

