package org.heeheepresso.orderapi.orderHistory

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.heeheepresso.orderapi.common.BaseEntity
import org.heeheepresso.orderapi.order.domain.model.OrderStatus
import org.heeheepresso.orderapi.orderHistory.aplication.OrderHistoryService
import org.heeheepresso.orderapi.orderHistory.domain.model.OrderMenuHistory
import org.heeheepresso.orderapi.orderHistory.domain.model.OrderMenuOptionHistory
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
                OrderMenuOptionHistoryCreateRequest(optionId = 1, name = "샷추가", price = BigDecimal(500), quantity = 2),
                OrderMenuOptionHistoryCreateRequest(optionId = 2, name = "딸기시럽", price = BigDecimal(100), quantity = 5),
                OrderMenuOptionHistoryCreateRequest(optionId = 3, name = "휘핑크림없이", price = BigDecimal(0), quantity = 1),
            )

            val orderMenuHistory = listOf(
                OrderMenuHistoryCreateRequest(
                    menuId = 1,
                    name = "아메리카노",
                    price = BigDecimal.ZERO,
                    quantity = 1,
                    orderMenuOptionHistoryCreateRequest = orderMenuOptionHistory
                ),
                OrderMenuHistoryCreateRequest(
                    menuId = 2,
                    name = "딸기라떼",
                    price = BigDecimal.ZERO,
                    quantity = 2,
                    orderMenuOptionHistoryCreateRequest = orderMenuOptionHistory
                ),
            )

            val orderMenuWithoutOptionHistory = listOf(
                OrderMenuHistoryCreateRequest(
                    menuId = 1,
                    name = "아메리카노",
                    price = BigDecimal.ZERO,
                    quantity = 1,
                    orderMenuOptionHistoryCreateRequest = emptyList()
                ),
                OrderMenuHistoryCreateRequest(
                    menuId = 2,
                    name = "딸기라떼",
                    price = BigDecimal.ZERO,
                    quantity = 2,
                    orderMenuOptionHistoryCreateRequest = emptyList()
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

            val requestWithMenuWithoutOption = OrderHistoryCreateRequest(
                orderId = 1,
                userId = 1,
                price = BigDecimal.ZERO,
                status = OrderStatus.WAITING,
                packagedYn = false,
                storedId = 1,
                paymentId = 1,
                orderMenuHistoryList = orderMenuWithoutOptionHistory,
            )

            // TODO 메뉴 없이 주문 히스토리 저장 요청이 오면 validation?
            When("주문 히스토리를 저장하면 (메뉴 없이)") {
                val now = LocalDateTime.now()
                val result = orderHistoryService.createOrderHistory(requestWithoutMenu)

                Then("저장한 주문 히스토리 그대로를 반환하고 주문 메뉴 리스트는 null 반환") {
                    result.orderId shouldBe requestWithoutMenu.orderId
                    result.userId shouldBe requestWithoutMenu.userId
                    result.price shouldBe requestWithoutMenu.price
                    result.status shouldBe requestWithoutMenu.status
                    result.packagedYn shouldBe requestWithoutMenu.packagedYn
                    result.storedId shouldBe requestWithoutMenu.storedId
                    result.paymentId shouldBe requestWithoutMenu.paymentId
                    result.orderMenuHistoryList?.size shouldBe 0
                    validateBaseEntity(result, now)
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
                    validateBaseEntity(result, now)

                    result.orderMenuHistoryList?.size shouldBe requestWithMenu.orderMenuHistoryList.size
                    result.orderMenuHistoryList?.forEach { menu ->
                        val menuReq = requestWithMenu.orderMenuHistoryList.find { it.menuId == menu.menuId }
                        validateMenu(menu, menuReq, now)

                        menu.orderMenuOptionHistoryList?.size shouldBe menuReq?.orderMenuOptionHistoryCreateRequest?.size
                        menu.orderMenuOptionHistoryList?.forEach { option ->
                            val optionReq =
                                menuReq?.orderMenuOptionHistoryCreateRequest?.find { it.optionId == option.optionId }
                            validateOption(option, optionReq, now)
                        }

                    }
                }
            }

            // TODO 옵션 없이 저장이 가능한가..? 기본 옵션을 줄지? 옵션이 없다면 알아서 무옵션으로?
            When("주문 메뉴와 함께(옵션 없이) 히스토리를 저장하면") {
                val now = LocalDateTime.now()
                val result = orderHistoryService.createOrderHistory(requestWithMenuWithoutOption)

                Then("저장한 주문 및 주문 메뉴 히스토리 그대로 반환") {
                    result.orderId shouldBe requestWithMenuWithoutOption.orderId
                    result.userId shouldBe requestWithMenuWithoutOption.userId
                    result.price shouldBe requestWithMenuWithoutOption.price
                    result.status shouldBe requestWithMenuWithoutOption.status
                    result.packagedYn shouldBe requestWithMenuWithoutOption.packagedYn
                    result.storedId shouldBe requestWithMenuWithoutOption.storedId
                    result.paymentId shouldBe requestWithMenuWithoutOption.paymentId
                    validateBaseEntity(result, now)

                    result.orderMenuHistoryList?.size shouldBe requestWithMenuWithoutOption.orderMenuHistoryList.size
                    result.orderMenuHistoryList?.forEach { orderMenuHistory ->
                        val menuReq = requestWithMenuWithoutOption.orderMenuHistoryList.find { it.menuId == orderMenuHistory.menuId }
                        validateMenu(orderMenuHistory, menuReq, now)

                        orderMenuHistory.orderMenuOptionHistoryList?.size shouldBe 0
                    }
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
                    validateBaseEntity(result, now)

                    orderHistory.orderMenuHistoryList?.size shouldBe requestWithMenu.orderMenuHistoryList.size
                    orderHistory.orderMenuHistoryList?.forEach { orderMenuHistory ->
                        val menuReq = requestWithMenu.orderMenuHistoryList.find { it.menuId == orderMenuHistory.menuId }
                        validateMenu(orderMenuHistory, menuReq, now)

                        orderMenuHistory.orderMenuOptionHistoryList?.size shouldBe menuReq?.orderMenuOptionHistoryCreateRequest?.size
                        orderMenuHistory.orderMenuOptionHistoryList?.forEach { option ->
                            val optionReq =
                                menuReq?.orderMenuOptionHistoryCreateRequest?.find { it.optionId == option.optionId }

                            validateOption(option, optionReq, now)
                        }
                    }
                }
            }
        }
    }

    private fun validateOption(
        option: OrderMenuOptionHistory,
        optionReq: OrderMenuOptionHistoryCreateRequest?,
        now: LocalDateTime
    ) {
        optionReq shouldNotBe null
        option.optionId shouldBe optionReq?.optionId
        option.name shouldBe optionReq?.name
        option.price.compareTo(optionReq?.price) shouldBe 0
        option.quantity shouldBe optionReq?.quantity
        validateBaseEntity(option, now)
    }

    private fun validateMenu(
        menu: OrderMenuHistory,
        menuReq: OrderMenuHistoryCreateRequest?,
        now: LocalDateTime
    ) {
        menuReq shouldNotBe null
        (menu.id ?: 0).shouldBeGreaterThan(0L)
        menu.menuId shouldBe menuReq?.menuId
        menu.price.compareTo(menuReq?.price) shouldBe 0
        menu.quantity shouldBe menuReq?.quantity
        validateBaseEntity(menu, now)
    }

    private fun validateBaseEntity(
        baseEntity: BaseEntity,
        now: LocalDateTime
    ) {
        // TODO 추후에 createdBy 실제 요청자로 검증
        baseEntity.createdBy shouldBe "system"
        baseEntity.createdDate shouldBeAfter now
        baseEntity.modifiedBy shouldBe "system"
        baseEntity.modifiedDate shouldBeAfter now
    }
}

