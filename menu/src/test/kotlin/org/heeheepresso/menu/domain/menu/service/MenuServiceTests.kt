package org.heeheepresso.menu.domain.menu.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.heeheepresso.menu.domain.menu.model.*
import org.heeheepresso.menu.interfaces.dto.ModifyStatusRequest
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal


@SpringBootTest
class MenuServiceTests(
    val menuService: MenuService
) : BehaviorSpec({
    Given("메뉴 서비스를 테스트") {
        When("메뉴에 아무것도 없을 때") {
            val result = menuService.findAll()

            Then("메뉴 리스트 전체 조회") {
                result.shouldHaveSize(0)
            }
        }
        When("메뉴를 저장하면") {

            val detail = MenuItemDetail(
                temperature = Temperature.ICE,
                thumbnailUrl = "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349",
            )
            val price = MenuItemPrice(
                storePrice = BigDecimal("2500.00"),
                salePrice = BigDecimal("2500.00"),
                takeOutPrice = BigDecimal("1800.00"),
            )
            //given
            val request = Menu(
                menuId = null,
                title = "아메리카노",
                subTitle = "Americano",
                description = "[진함 고소함] 견과류 풍미와 초콜릿처럼 달콤 쌉싸름한 맛이 밸런스 있게 어우러진 균형잡힌 바디감의 커피",
                category = Category.COFFEE,
                flagType = null,
                status = MenuStatus.SELLING,
                menuItemDetail = detail,
                menuItemPrice = price,
            )
            //when
            val result = menuService.save(request)
            Then("메뉴를 저장한다") {
                result.menuId shouldBe request.menuId
                result.title shouldBe request.title
                result.subTitle shouldBe request.subTitle
                result.description shouldBe request.description
                result.category shouldBe request.category
                result.flagType shouldBe request.flagType
                result.status shouldBe request.status
                result.menuItemDetail shouldBe request.menuItemDetail
                result.menuItemPrice shouldBe request.menuItemPrice
            }
        }

        When("메뉴 한건을 조회하면") {
            val detail = MenuItemDetail(
                temperature = Temperature.ICE,
                thumbnailUrl = "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349",
            )
            val price = MenuItemPrice(
                storePrice = BigDecimal("2500.00"),
                salePrice = BigDecimal("2500.00"),
                takeOutPrice = BigDecimal("1800.00"),
            )
            //given
            val input = Menu(
                menuId = 2L,
                title = "아메리카노",
                subTitle = "Americano",
                description = "[진함 고소함] 견과류 풍미와 초콜릿처럼 달콤 쌉싸름한 맛이 밸런스 있게 어우러진 균형잡힌 바디감의 커피",
                category = Category.COFFEE,
                flagType = null,
                status = MenuStatus.SELLING,
                menuItemDetail = detail,
                menuItemPrice = price,
            )
            val savedInput = menuService.save(input)
            //when
            val result = menuService.findById(savedInput.menuId!!)

            Then("조회한 메뉴아이디를 반환") {
                result.menuId shouldBe input.menuId
                result.title shouldBe input.title
                result.subTitle shouldBe input.subTitle
                result.description shouldBe input.description
                result.category shouldBe input.category
                result.flagType shouldBe input.flagType
                result.status shouldBe input.status
                result.menuItemDetail shouldBe input.menuItemDetail
                result.menuItemPrice shouldBe input.menuItemPrice
            }
        }

        When("메뉴를 전체 조회하면") {
            val detail = MenuItemDetail(
                temperature = Temperature.ICE,
                thumbnailUrl = "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349",
            )
            val price = MenuItemPrice(
                storePrice = BigDecimal("2500.00"),
                salePrice = BigDecimal("2500.00"),
                takeOutPrice = BigDecimal("1800.00"),
            )
            //given
            val input = Menu(
                menuId = 3L,
                title = "아메리카노",
                subTitle = "Americano",
                description = "[진함 고소함] 견과류 풍미와 초콜릿처럼 달콤 쌉싸름한 맛이 밸런스 있게 어우러진 균형잡힌 바디감의 커피",
                category = Category.COFFEE,
                flagType = null,
                status = MenuStatus.SELLING,
                menuItemDetail = detail,
                menuItemPrice = price,
            )
            menuService.save(input)

            val result = menuService.findAll()

            Then("메뉴 리스트 전체 조회") {
                result.shouldHaveSize(3)
            }
        }
        When("상태 수정") {
            val detail = MenuItemDetail(
                temperature = Temperature.ICE,
                thumbnailUrl = "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349",
            )
            val price = MenuItemPrice(
                storePrice = BigDecimal("2500.00"),
                salePrice = BigDecimal("2500.00"),
                takeOutPrice = BigDecimal("1800.00"),
            )
            //given
            val input = Menu(
                menuId = 3L,
                title = "아메리카노",
                subTitle = "Americano",
                description = "[진함 고소함] 견과류 풍미와 초콜릿처럼 달콤 쌉싸름한 맛이 밸런스 있게 어우러진 균형잡힌 바디감의 커피",
                category = Category.COFFEE,
                flagType = null,
                status = MenuStatus.SELLING,
                menuItemDetail = detail,
                menuItemPrice = price,
            )
            menuService.save(input)

            val request = ModifyStatusRequest(
                status = MenuStatus.SOLD_OUT,
            )
            menuService.modifyStatus(3L, request)

            val result = menuService.findById(3L)

            Then("메뉴 상태 변경") {
                result.status shouldBe request.status
            }
        }

    }
})

