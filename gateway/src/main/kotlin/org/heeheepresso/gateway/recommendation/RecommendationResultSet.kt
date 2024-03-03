package org.heeheepresso.gateway.recommendation

data class RecommendationResultSet(
    val results: List<RecommendationResult>
) {
    fun getTotalMenuIds(): List<Long> {
        return this.results
            .flatMap { it.recommendedMenus }
            .map { it.menuId }
    }
}
