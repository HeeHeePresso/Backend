package org.heeheepresso.gateway.home

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(
        private val homeService: HomeService
) {

    @GetMapping("/home")
    suspend fun getHome(
            @RequestParam("title") titles: List<String>,
            @RequestParam("userId") userId: Long
    ): HomePageResponse {
        return homeService.getHomeData(userId, titles)
    }
}