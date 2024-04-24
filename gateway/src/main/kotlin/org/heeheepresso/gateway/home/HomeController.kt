package org.heeheepresso.gateway.home

import io.swagger.v3.oas.annotations.Operation
import org.heeheepresso.gateway.common.response.GatewayResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(
        private val homeService: HomeService
) {

    @Operation(summary = "Home Page API")
    @GetMapping("/home")
    suspend fun getHome(
            @RequestParam("userId") userId: Long
    ): GatewayResponse<HomePageResponse> {
        return GatewayResponse(homeService.getHomeData(userId))
    }
}