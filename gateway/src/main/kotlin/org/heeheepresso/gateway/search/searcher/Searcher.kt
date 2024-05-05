package org.heeheepresso.gateway.search.searcher

import org.heeheepresso.gateway.search.SearchResult
import org.heeheepresso.gateway.search.request.SearchRequest
import reactor.core.publisher.Mono

interface Searcher {
    suspend fun search(request: SearchRequest): Mono<SearchResult>
}