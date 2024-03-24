package org.heeheepresso.orderapi.order.application

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.heeheepresso.orderapi.order.domain.model.Money
import org.heeheepresso.orderapi.order.domain.model.Option
import org.heeheepresso.orderapi.order.domain.model.OrderStatus
import org.heeheepresso.orderapi.order.dto.request.OrderCreateRequest
import org.heeheepresso.orderapi.order.dto.request.OrderMenuCreateRequest
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrderServiceIntegrationTest(
    val orderService: OrderService,
) : DescribeSpec({

    describe("OrderService 클래스의") {
        val request = request()
        context("createOrder 메소드를 실행하면") {
            val result = orderService.createOrder(request)
            it("OrderCreateRequest에 담긴 값으로 Order를 저장 후 반환한다") {
                result.id shouldNotBe null
                result.buyer.userId shouldBe request.userId
                result.store.storeId shouldBe request.storedId
                result.store.storeName shouldBe request.storeName
                result.paymentInfo.paymentId shouldBe request.paymentId
                result.paymentInfo.amount.getIntValue() shouldBe request.amount
                result.orderMenuList shouldHaveSize request.orderMenuList.size
                result.packagedYn shouldBe request.packagedYn
            }

            it("OrderStatus는 REQUESTED(요청) 상태이다") {
                result.status shouldBe OrderStatus.REQUESTED
            }
        }

        val order = orderService.createOrder(request)
        val orderId = order.id ?: 1L
        context("getOrder 메소드를 실행했을 때 반환값 OrderResponse의") {
            val result = orderService.getOrder(orderId)
            it("orderId 값은 요청 orderId 값과 동일하다") {
                result.orderId shouldBe orderId
            }
            it("store 값은 요청 storeName 값과 동일하다") {
                result.store shouldBe request.storeName
            }
            it("totalAmount 값은 요청 amount 값과 동일하다") {
                result.store shouldBe request.storeName
            }
            it("menuList 개수는 요청 메뉴 개수와 동일하다") {
                result.menuList shouldHaveSize request.orderMenuList.size
            }
            val resultMenuList = result.menuList
            val requestMenuList = request.orderMenuList
            it("menuList의 각 요소의 값은 요청 메뉴 값과 동일하다") {
                for ((idx, resultMenu) in resultMenuList.withIndex()) {
                    val requestMenu = requestMenuList[idx]
                    resultMenu.name shouldBe requestMenu.menuName
                    resultMenu.price shouldBe requestMenu.price
                    resultMenu.totalAmount shouldBe requestMenu.totalAmount
                }
            }
            val resultOptions = resultMenuList[0].options
            val requestOptions = requestMenuList[0].options
            it("응답 메뉴의 options 개수는 요청 메뉴의 옵션 개수와 동일하다") {
                resultOptions shouldHaveSize requestOptions.size
            }
            it("응답 메뉴의 options 값은 요청 메뉴의 옵션 값과 동일하다") {
                for ((idx, resultOption) in resultOptions.withIndex()) {
                    val requestOption = requestOptions[idx]
                    resultOption.price shouldBe requestOption.price.getIntValue()
                    resultOption.name shouldBe requestOption.name
                    resultOption.quantity shouldBe requestOption.quantity
                }
            }
        }
    }

})

fun request(): OrderCreateRequest {
    return OrderCreateRequest(
        1,
        2000,
        true,
        1,
        "가게1",
        1,
        listOf(
            OrderMenuCreateRequest(1, "메뉴1", 1000, 1,
                totalAmount = 1500,
                options = listOf(
                    Option("HOT", Money(0), 1),
                    Option("시럽", Money(500), 1)
                )
            ),
            OrderMenuCreateRequest(2, "메뉴2", 1000, 1,
                totalAmount = 1500,
                options = listOf(
                    Option("HOT", Money(0), 1),
                    Option("시럽", Money(500), 1),
                )
            ),
        )
    )
}



