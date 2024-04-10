package org.heeheepresso.gateway.home

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HomeServiceTest(
        @Autowired private val homeService: HomeService
) : BehaviorSpec({
    Given("홈 화면 API") {
        val userId = 1L
        When("홈 화면에 대한 데이터를 조회하면") {
            val result = homeService.getHomeData(userId)
            Then("여러 서비스로부터 취합한 결과를 반환한다.") {
                result.eventUrls.size shouldBe 3
                result.menuInfos[0].menus.size shouldBe 2
                result.menuInfos[0].handler shouldContain "SEASON_RECOMMENDED"
                result.menuInfos[0].menus.stream().map { menu -> menu.name }.toList() shouldContainAll listOf("신승건", "테스트")
            }
        }
    }
})