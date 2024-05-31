package org.heeheepresso.gateway.menu.category.controller

import org.heeheepresso.gateway.common.response.GatewayResponse
import org.heeheepresso.gateway.menu.category.CategoryMenusResponse
import org.heeheepresso.gateway.menu.category.MenuCategory
import org.heeheepresso.gateway.menu.category.service.MenuCategoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class MenuCategoryController(
        private val menuCategoryService: MenuCategoryService
) {

    @GetMapping("/menus")
    suspend fun getMenusByCategory(@RequestParam("category") menuCategory: MenuCategory, @RequestParam("userId") userId: Long): GatewayResponse<CategoryMenusResponse> {
        return GatewayResponse(menuCategoryService.getMenusByCategory(menuCategory, userId))
    }
}