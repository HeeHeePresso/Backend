package org.heeheepresso.gateway.menu.category.dto

import org.heeheepresso.gateway.recommendation.RecommendedMenu

data class MenuCategoryList(
    val menus: List<RecommendedMenu>?
)