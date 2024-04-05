package org.heeheepresso.menu.interfaces.api

import io.kotest.core.spec.style.BehaviorSpec
import org.heeheepresso.menu.domain.menu.model.*
import org.heeheepresso.menu.domain.menu.service.MenuService
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@WebMvcTest(controllers = [MenuController::class])
@AutoConfigureMockMvc
class MenuControllerTests(
        @Autowired val mockMvc: MockMvc,
        @MockBean val menuService: MenuService,
) : BehaviorSpec({

    Given("getMenusByMenuIds 테스트") {
        BDDMockito
                .given(menuService.findMenus(listOf(1L)))
                .willReturn(listOf(
                        Menu(
                                menuId = 1L,
                                title = "샘플 메뉴 01",
                                subTitle = "Sample Menu 01",
                                description = "Sample Menu for Test",
                                category = Category.COFFEE,
                                flagType = null,
                                status = MenuStatus.SELLING,
                                menuItemDetail = MenuItemDetail(
                                        temperature = Temperature.ICE,
                                        thumbnailUrl = "https://github.com/HeeHeePresso/Backend/assets/49651099/58dca505-0a34-4926-a0aa-336a91c864c9",
                                ),
                                menuItemPrice = MenuItemPrice(
                                        storePrice = BigDecimal("3000.00"),
                                        salePrice = BigDecimal("2700.00"),
                                        takeOutPrice = BigDecimal("2500.00"),
                                ),
                        )))
        BDDMockito
                .given(menuService.findMenus(listOf(1L, 2L)))
                .willReturn(listOf(
                        Menu(
                                menuId = 1L,
                                title = "샘플 메뉴 01",
                                subTitle = "Sample Menu 01",
                                description = "Sample Menu for Test",
                                category = Category.COFFEE,
                                flagType = null,
                                status = MenuStatus.SELLING,
                                menuItemDetail = MenuItemDetail(
                                        temperature = Temperature.ICE,
                                        thumbnailUrl = "https://github.com/HeeHeePresso/Backend/assets/49651099/58dca505-0a34-4926-a0aa-336a91c864c9",
                                ),
                                menuItemPrice = MenuItemPrice(
                                        storePrice = BigDecimal("3000.00"),
                                        salePrice = BigDecimal("2700.00"),
                                        takeOutPrice = BigDecimal("2500.00"),
                                ),
                        ),
                        Menu(
                                menuId = 2L,
                                title = "샘플 메뉴 02",
                                subTitle = "Sample Menu 02",
                                description = "Sample Menu for Test",
                                category = Category.JUICE_AND_DRINK,
                                flagType = null,
                                status = MenuStatus.SELLING,
                                menuItemDetail = MenuItemDetail(
                                        temperature = Temperature.ICE,
                                        thumbnailUrl = "https://github.com/HeeHeePresso/Backend/assets/49651099/2ba003c2-ddd6-41cf-bb7e-e3a18b7e2eec",
                                ),
                                menuItemPrice = MenuItemPrice(
                                        storePrice = BigDecimal("3000.00"),
                                        salePrice = BigDecimal("2700.00"),
                                        takeOutPrice = BigDecimal("2500.00"),
                                ),
                        ),
                ))

        When("menu id 목록이 비어있을 때") {
            Then("http 상태 코드 200 반환함 그리고 목록 전체를 반환함") {
                mockMvc
                        .perform(MockMvcRequestBuilders.get("/menus"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
            }
        }

        When("menu id 목록으로 1건이 들어왔을 때") {
            Then("http 상태 코드 200 반환함 그리고 데이터가 1건인 목록을 반환함") {
                mockMvc
                        .perform(
                                MockMvcRequestBuilders.get("/menus")
                                        .param("id", "1")
                        )
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].menuId").value("1"))
            }
        }

        When("menu id 목록으로 2건이 들어왔을 때") {
            Then("http 상태 코드 200 반환함 그리고 데이터가 2건인 목록을 반환함") {
                mockMvc
                        .perform(
                                MockMvcRequestBuilders.get("/menus")
                                        .param("id", "1")
                                        .param("id", "2")
                        )
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].menuId").value("1"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[1].menuId").value("2"))
            }
        }
    }
})