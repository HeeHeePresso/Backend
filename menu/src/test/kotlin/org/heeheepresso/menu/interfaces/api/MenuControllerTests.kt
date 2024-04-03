package org.heeheepresso.menu.interfaces.api

import io.kotest.core.spec.style.BehaviorSpec
import org.heeheepresso.menu.domain.menu.service.MenuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest(controllers = [MenuController::class])
@AutoConfigureMockMvc
class MenuControllerTests(
        @Autowired val mockMvc: MockMvc,
        @MockBean val menuService: MenuService,
) : BehaviorSpec({

    Given("getMenusByMenuIds 테스트") {

        When("menu id 목록이 비어있을 때") {
            Then("http 상태 코드 400 반환함") {

            }
        }

        When("menu id 목록으로 1건이 들어왔을 때") {
            Then("http 상태 코드 200 반환함") {

            }

            Then("데이터가 1건인 목록을 반환함") {

            }
        }

        When("menu id 목록으로 2건이 들어왔을 때") {
            Then("http 상태 코드 200 반환함") {

            }

            Then("데이터가 2건인 목록을 반환함") {

            }
        }
    }
})