package org.heeheepresso.gateway.search.searcher

import org.heeheepresso.gateway.search.SearchResult
import org.heeheepresso.gateway.search.request.SearchRequest
import org.heeheepresso.gateway.search.searcher.SearcherType.EVENT
import org.heeheepresso.gateway.search.searcher.SearcherType.RECOMMENDATION
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SearcherGateway(
    private val eventSearcher: EventSearcher,
    private val recommendationSearcher: RecommendationSearcher,
    private val exceptionSearcher: ExceptionSearcher,
) {

    suspend fun search(searcherType: SearcherType, searchRequest: SearchRequest): Mono<SearchResult> {
        return getSearcher(searcherType).search(searchRequest)
    }

    private fun getSearcher(searcherType: SearcherType): Searcher {
        return when(searcherType) {
            EVENT -> eventSearcher
            RECOMMENDATION -> recommendationSearcher
            else -> exceptionSearcher
        }
    }
}