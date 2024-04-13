package org.heeheepresso.gateway.menu.moreinfo

import org.heeheepresso.gateway.recommendation.RecommendationResult
import org.heeheepresso.gateway.recommendation.RecommendedResultMenu

interface MoreInfo {
    suspend fun setMoreInfo(resultSet: List<RecommendationResult>, resultMenus: MutableList<RecommendedResultMenu>)
}