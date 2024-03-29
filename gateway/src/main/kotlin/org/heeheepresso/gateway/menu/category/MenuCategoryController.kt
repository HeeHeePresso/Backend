package org.heeheepresso.gateway.menu.category

import mu.KotlinLogging
import org.heeheepresso.gateway.menu.category.dto.MenuCategoryList
import org.heeheepresso.gateway.menu.category.dto.RecommendedPageResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu")
class MenuCategoryController(
        private val menuCategoryService: MenuCategoryService
) {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/recommendation")
    suspend fun getRecommendedPage(@RequestParam("userId") userId: Long): RecommendedPageResponse {
        return menuCategoryService.getRecommendedPage(userId)
    }

    @GetMapping("/{category}")
    suspend fun getPageByCategory(
            @RequestParam("userId") userId: Long,
            @PathVariable("category") category: String): MenuCategoryList {
        return menuCategoryService.getPageByCategory(userId, category)
    }
}