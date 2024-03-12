package org.heeheepresso.gateway.recommendation

data class RecommendationResultSet(
    val results: List<RecommendationResult>
) {
    fun getMenuList(handler: RecommendationHandler): List<RecommendedMenu>? {
        return this.results
            .firstOrNull { it.handler == handler.name }
            ?.recommendedMenus
    }
}
