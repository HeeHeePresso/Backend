package org.heeheepresso.gateway.menu.category

import org.heeheepresso.gateway.home.MenuResult

data class CategoryMenusResponse(
        val menuCategory: MenuCategory,
        val menuInfos: List<MenuResult>
)