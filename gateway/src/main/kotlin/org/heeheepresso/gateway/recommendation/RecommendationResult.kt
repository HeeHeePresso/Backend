package org.heeheepresso.gateway.recommendation

data class RecommendationResult(
        val recommendedMenus: List<RecommendedMenu>,
        val handler: String,
)
