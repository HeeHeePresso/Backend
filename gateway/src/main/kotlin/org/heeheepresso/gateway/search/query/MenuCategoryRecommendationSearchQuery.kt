package org.heeheepresso.gateway.search.query

import org.heeheepresso.gateway.search.SearchContext
import org.heeheepresso.gateway.search.request.SearchRequest
import org.heeheepresso.gateway.search.request.SearchRequestHandler
import org.heeheepresso.gateway.search.searcher.SearcherType
import org.springframework.stereotype.Service

@Service
class MenuCategoryRecommendationSearchQuery : SearchQuery {

    companion object {
        private const val CAROUSEL_PAGE_SIZE = 9
    }

    override fun getSearcher(): SearcherType {
        return SearcherType.RECOMMENDATION
    }

    override fun buildRequest(searchContext: SearchContext): SearchRequest {
        return SearchRequest(userId = searchContext.getUserInfo().userId,
                handler = SearchRequestHandler.MENU_CATEGORY,
                pageSize = CAROUSEL_PAGE_SIZE,
                category = searchContext.category)
    }
}