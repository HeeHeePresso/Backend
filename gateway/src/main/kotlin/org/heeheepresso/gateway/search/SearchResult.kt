package org.heeheepresso.gateway.search

import org.heeheepresso.gateway.common.response.StatusCode
import org.heeheepresso.gateway.search.request.SearchRequestHandler
import org.heeheepresso.gateway.search.searcher.SearcherType

data class SearchResult(
    val statusCode: StatusCode,
    val searcherType: SearcherType,
    val searchRequestHandler: SearchRequestHandler,
    val imageUrls: List<String>? = null,
    val storeIds: List<Long>? = null,
)
