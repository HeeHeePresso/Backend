package org.heeheepresso.gateway.search.query

import org.heeheepresso.gateway.search.SearchContext
import org.heeheepresso.gateway.search.request.SearchRequest
import org.heeheepresso.gateway.search.request.SearchRequestHandler
import org.heeheepresso.gateway.search.searcher.SearcherType
import org.springframework.stereotype.Service

@Service
class EventSearchQuery: SearchQuery {
    override fun getSearcher(): SearcherType {
        return SearcherType.EVENT
    }

    override fun buildRequest(searchContext: SearchContext): SearchRequest {
        return SearchRequest(
            userId = searchContext.getUserInfo().userId,
            handler = SearchRequestHandler.HOME,
            pageSize = 3,
            )
    }
}