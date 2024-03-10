package org.heeheepresso.gateway.menu.moreinfo

import org.heeheepresso.gateway.recommendation.RecommendationResult

interface MoreInfo {
    suspend fun addMoreInfo(resultSet: List<RecommendationResult>)
}