package org.heeheepresso.gateway.recommendation

import org.heeheepresso.gateway.menu.domain.Menu
import org.heeheepresso.gateway.menu.domain.MenuDetail

data class RecommendationResultSet(
    val results: List<RecommendationResult>
) {
    fun getTotalMenuIds(): List<Long> {
        return this.results
            .flatMap { it.recommendedMenus }
            .map { it.menuId }
    }

    fun getMenuList(handler: RecommendationHandler, menuDetail: Map<Long, MenuDetail>): List<Menu>? {
        return this.results
            .firstOrNull { it.handler == handler.name }
            ?.getMenus(menuDetail)
    }
}
