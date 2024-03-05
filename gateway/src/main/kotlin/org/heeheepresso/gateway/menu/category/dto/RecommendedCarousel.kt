package org.heeheepresso.gateway.menu.category.dto

import org.heeheepresso.gateway.menu.domain.Menu

data class RecommendedCarousel(
    val handler: String,
    val menus: List<Menu>
)