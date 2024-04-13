package org.heeheepresso.gateway.menu.category

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MenuCategoryServiceTest @Autowired constructor(
        private val menuCategoryService: MenuCategoryService
) : BehaviorSpec({
    Given("존재하는 메뉴에 대해") {
        val testCategory = "TEA_AND_ADE"
        val userId = 1L
        When("특정 카테고리로 메뉴 목록을 조회하면") {
            val result = menuCategoryService.getPageByCategory(userId, testCategory)
            Then("카테고리 별 추천 메뉴 목록을 반환한다.") {
                result.menus!!.size shouldBe 2
                result.menus!!.stream().map { it.name }.toList() shouldContainAll listOf("신승건", "테스트")
            }
        }
    }
})