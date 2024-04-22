package org.heeheepresso.gateway.home

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(
        private val homeService: HomeService
) {

    @Operation(summary = "Home Page API")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success", content = [
            Content(mediaType = "application/json", array = ArraySchema(schema = Schema(implementation = HomePageResponse::class)))
        ])
    ])
    @GetMapping("/home")
    suspend fun getHome(
            @RequestParam("userId") userId: Long
    ): HomePageResponse {
        return homeService.getHomeData(userId)
    }
}