package org.heeheepresso.gateway.recommendation.client

import org.heeheepresso.gateway.recommendation.RecommendationResult

data class HomeRecommendResponse(
        val success: Boolean,
        val error: List<ErrorResponse>,
        val timestamp: String,
        val data: RecommendationResult
)
