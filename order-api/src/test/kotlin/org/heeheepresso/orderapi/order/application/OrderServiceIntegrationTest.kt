package org.heeheepresso.orderapi.order.application

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.heeheepresso.orderapi.order.domain.model.OrderStatus
import org.heeheepresso.orderapi.order.dto.request.OrderCreateRequest
import org.heeheepresso.orderapi.order.dto.request.OrderMenuCreateRequest
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

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
                result.paymentInfo.paymentId shouldBe request.paymentId
                result.orderMenuList shouldHaveSize request.orderMenuList.size
                result.packagedYn shouldBe request.packagedYn
            }

            it("OrderStatus는 REQUESTED(요청) 상태이다") {
                result.status shouldBe OrderStatus.REQUESTED
            }
        }

    }

})

fun request(): OrderCreateRequest {
    return OrderCreateRequest(
        1,
        BigDecimal("2000"),
        true,
        1,
        1,
        listOf(
            OrderMenuCreateRequest(1, BigDecimal("1000"), 1),
            OrderMenuCreateRequest(2, BigDecimal("1000"), 1),
        )
    )
}



