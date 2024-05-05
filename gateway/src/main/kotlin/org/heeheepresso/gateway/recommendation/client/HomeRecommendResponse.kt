package org.heeheepresso.gateway.recommendation.client

import org.heeheepresso.gateway.recommendation.RecommendationResult

data class HomeRecommendResponse(
        val success: Boolean,
        val data: RecommendationResult,
        val error: ErrorResponse?,
        val timestamp: String
)
