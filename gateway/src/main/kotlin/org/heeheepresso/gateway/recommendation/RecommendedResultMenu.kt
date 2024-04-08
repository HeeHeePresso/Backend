package org.heeheepresso.gateway.recommendation

import org.heeheepresso.gateway.menu.domain.MenuInfo

data class RecommendedResultMenu(
        val recommendedMenus: List<MenuInfo>,
        val handler: String
)