package org.heeheepresso.gateway.search.query

import org.heeheepresso.gateway.search.SearchContext
import org.heeheepresso.gateway.search.request.SearchRequest
import org.heeheepresso.gateway.search.searcher.SearcherType

interface SearchQuery {
    fun getSearcher(): SearcherType
    fun buildRequest(searchContext: SearchContext): SearchRequest
}