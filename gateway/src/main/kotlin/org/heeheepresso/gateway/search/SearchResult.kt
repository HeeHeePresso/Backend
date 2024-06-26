package org.heeheepresso.gateway.search

import org.heeheepresso.gateway.common.response.StatusCode
import org.heeheepresso.gateway.search.request.SearchRequestHandler
import org.heeheepresso.gateway.search.searcher.SearcherType

data class SearchResult(
    val statusCode: StatusCode,
    val searcherType: SearcherType,
    val searchRequestHandler: SearchRequestHandler,
    var searched: List<Any>? = null
)
