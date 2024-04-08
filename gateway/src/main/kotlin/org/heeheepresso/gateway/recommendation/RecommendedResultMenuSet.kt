package org.heeheepresso.gateway.recommendation

import org.heeheepresso.gateway.home.MenuResult
import org.heeheepresso.gateway.menu.domain.MenuInfo

data class RecommendedResultMenuSet(
        val results: List<RecommendedResultMenu>
) {
    fun getMenuList(handler: RecommendationHandler): List<MenuInfo>? {
        return this.results
                .firstOrNull { it.handler == handler.name }
                ?.recommendedMenus
    }

    fun mapHandlerWithMenus(): List<MenuResult> {
        return this.results
                .map { result ->
                    MenuResult(result.handler, result.recommendedMenus.map { it.getBaseInfo() })
                }
    }
}