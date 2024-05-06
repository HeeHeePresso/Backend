package org.heeheepresso.gateway.search.searcher

import kotlinx.coroutines.reactor.mono
import org.heeheepresso.gateway.common.response.StatusCode
import org.heeheepresso.gateway.search.SearchResult
import org.heeheepresso.gateway.search.request.SearchRequest
import org.heeheepresso.gateway.search.request.SearchRequestHandler
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ExceptionSearcher: Searcher {
    override suspend fun search(request: SearchRequest): Mono<SearchResult> {
        return mono {
                SearchResult(
                    statusCode = StatusCode.NOT_VALID_INPUT,
                    searcherType = SearcherType.UNKNOWN,
                    searchRequestHandler = SearchRequestHandler.UNKNOWN,
                )
        }
    }
}
