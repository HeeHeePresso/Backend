package org.heeheepresso.gateway.menu.category.dto

import org.heeheepresso.gateway.recommendation.RecommendedMenu

data class RecommendedCarousel(
    val handler: String,
    val menus: List<RecommendedMenu>
)