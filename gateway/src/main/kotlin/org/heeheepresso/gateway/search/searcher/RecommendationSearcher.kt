package org.heeheepresso.gateway.search.searcher

import org.heeheepresso.gateway.common.response.StatusCode.SERVER_ERROR
import org.heeheepresso.gateway.common.response.StatusCode.SUCCESS
import org.heeheepresso.gateway.menu.category.RecommendationFilterUtils
import org.heeheepresso.gateway.recommendation.RecommendedRequest
import org.heeheepresso.gateway.recommendation.client.RecommendController
import org.heeheepresso.gateway.search.SearchResult
import org.heeheepresso.gateway.search.request.SearchRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class RecommendationSearcher(
    private val recommendController: RecommendController
): Searcher {
    override suspend fun search(request: SearchRequest): Mono<SearchResult> {
        val recommendedRequest = buildRecommendedRequest(request)
        return recommendController
            .callRecommendMenus(recommendedRequest)
            .map {
                SearchResult(
                    statusCode = if(it.success) SUCCESS else SERVER_ERROR,
                    searcherType = SearcherType.RECOMMENDATION,
                    searchRequestHandler = request.handler,
                    searched = it.data.recommendedMenus.map{ menu -> menu.menuId}
            ) }
    }

    private fun buildRecommendedRequest(request: SearchRequest) = RecommendedRequest(
        handler = request.handler.name,
        where = request.category?.let { category -> RecommendationFilterUtils.addCategoryFilter(category) },
        userId = request.userId,
        storeId = request.storeId,
        pageSize = request.pageSize,
        offset = request.offset
    )
}