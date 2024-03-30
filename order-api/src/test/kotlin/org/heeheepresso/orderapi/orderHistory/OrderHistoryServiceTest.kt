package org.heeheepresso.orderapi.orderHistory

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.heeheepresso.orderapi.order.domain.model.OrderStatus
import org.heeheepresso.orderapi.orderHistory.aplication.OrderHistoryService
import org.heeheepresso.orderapi.orderHistory.dto.request.OrderHistoryCreateRequest
import org.heeheepresso.orderapi.orderHistory.dto.request.OrderMenuHistoryCreateRequest
import org.heeheepresso.orderapi.orderHistory.dto.request.OrderMenuOptionHistoryCreateRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest
class OrderHistoryServiceTest @Autowired constructor(
    private val orderHistoryService: OrderHistoryService
) : BehaviorSpec() {

    init {
        Given("주문 히스토리 서비스 테스트") {
            val requestWithoutMenu = OrderHistoryCreateRequest(
                orderId = 1,
                userId = 1,
                price = BigDecimal.ZERO,
                status = OrderStatus.WAITING,
                packagedYn = false,
                storedId = 1,
                paymentId = 1,
                orderMenuHistoryList = emptyList(),
            )

            val orderMenuOptionHistory = listOf(
                OrderMenuOptionHistoryCreateRequest(option = "샷추가", price = BigDecimal(500), quantity = 2),
                OrderMenuOptionHistoryCreateRequest(option = "딸기시럽", price = BigDecimal(100), quantity = 2),
                OrderMenuOptionHistoryCreateRequest(option = "휘핑크림없이", price = BigDecimal(0), quantity = 1),
            )

            val orderMenuHistory = listOf(
                OrderMenuHistoryCreateRequest(
                    menuId = 1,
                    price = BigDecimal.ZERO,
                    quantity = 1,
                    orderMenuOptionHistoryCreateRequest = orderMenuOptionHistory
                ),
                OrderMenuHistoryCreateRequest(
                    menuId = 2,
                    price = BigDecimal.ZERO,
                    quantity = 2,
                    orderMenuOptionHistoryCreateRequest = orderMenuOptionHistory
                ),
            )

            val requestWithMenu = OrderHistoryCreateRequest(
                orderId = 1,
                userId = 1,
                price = BigDecimal.ZERO,
                status = OrderStatus.WAITING,
                packagedYn = false,
                storedId = 1,
                paymentId = 1,
                orderMenuHistoryList = orderMenuHistory,
            )

            When("주문 히스토리를 저장하면") {
                val now = LocalDateTime.now()
                val result = orderHistoryService.createOrderHistory(requestWithoutMenu)

                Then("저장한 주문 히스토리 그대로를 반환하고 주문 메뉴 리스트는 null 반환 (주문 메뉴는 별도의 서비스에서 처리)") {
                    result.orderId shouldBe requestWithoutMenu.orderId
                    result.userId shouldBe requestWithoutMenu.userId
                    result.price shouldBe requestWithoutMenu.price
                    result.status shouldBe requestWithoutMenu.status
                    result.packagedYn shouldBe requestWithoutMenu.packagedYn
                    result.storedId shouldBe requestWithoutMenu.storedId
                    result.paymentId shouldBe requestWithoutMenu.paymentId
                    result.orderMenuHistoryList?.size shouldBe 0
                    result.createdBy shouldBe "system"
                    result.createdDate shouldBeAfter now
                    result.modifiedBy shouldBe "system"
                    result.modifiedDate shouldBeAfter now
                }
            }

            When("주문 메뉴와 함께 히스토리를 저장하면") {
                val now = LocalDateTime.now()
                val result = orderHistoryService.createOrderHistory(requestWithMenu)

                Then("저장한 주문 및 주문 메뉴 히스토리 그대로 반환") {
                    result.orderId shouldBe requestWithMenu.orderId
                    result.userId shouldBe requestWithMenu.userId
                    result.price shouldBe requestWithMenu.price
                    result.status shouldBe requestWithMenu.status
                    result.packagedYn shouldBe requestWithMenu.packagedYn
                    result.storedId shouldBe requestWithMenu.storedId
                    result.paymentId shouldBe requestWithMenu.paymentId
                    result.orderMenuHistoryList?.size shouldBe 2
                    requestWithMenu.orderMenuHistoryList.forEachIndexed { index, orderMenuHistory ->
                        orderMenuHistory.menuId shouldBe requestWithMenu.orderMenuHistoryList[index].menuId
                        orderMenuHistory.price shouldBe requestWithMenu.orderMenuHistoryList[index].price
                        orderMenuHistory.quantity shouldBe requestWithMenu.orderMenuHistoryList[index].quantity
                    }
                    result.createdBy shouldBe "system"
                    result.createdDate shouldBeAfter now
                    result.modifiedBy shouldBe "system"
                    result.modifiedDate shouldBeAfter now
                }
            }

            When("주문 히스토리를 ID로 조회하면 (querydsl 테스트)") {
                val now = LocalDateTime.now()
                val result = orderHistoryService.createOrderHistory(requestWithMenu)
                val orderHistory = orderHistoryService.getOrderHistoryById(result.id!!)

                Then("주문 히스토리와 그에 딸려 있는 주문 메뉴 히스토리까지 반환 (N+1 문제 없이)") {
                    orderHistory.orderId shouldBe requestWithMenu.orderId
                    orderHistory.userId shouldBe requestWithMenu.userId
                    orderHistory.price.compareTo(requestWithMenu.price) shouldBe 0
                    orderHistory.status shouldBe requestWithMenu.status
                    orderHistory.packagedYn shouldBe requestWithMenu.packagedYn
                    orderHistory.storedId shouldBe requestWithMenu.storedId
                    orderHistory.paymentId shouldBe requestWithMenu.paymentId
                    orderHistory.orderMenuHistoryList?.size shouldBe requestWithMenu.orderMenuHistoryList.size
                    orderHistory.orderMenuHistoryList?.forEach { orderMenuHistory ->
                        val data = requestWithMenu.orderMenuHistoryList.find { it.menuId == orderMenuHistory.menuId }
                        data shouldNotBe null
                        orderMenuHistory.menuId shouldBe data?.menuId
                        orderMenuHistory.price.compareTo(data?.price) shouldBe 0
                        orderMenuHistory.quantity shouldBe data?.quantity
                        orderMenuHistory.orderMenuOptionHistoryList?.size shouldBe data?.orderMenuOptionHistoryCreateRequest?.size
                    }
                    result.createdBy shouldBe "system"
                    result.createdDate shouldBeAfter now
                    result.modifiedBy shouldBe "system"
                    result.modifiedDate shouldBeAfter now
                }
            }
        }
    }
}

