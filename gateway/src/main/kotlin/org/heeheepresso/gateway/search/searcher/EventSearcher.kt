package org.heeheepresso.gateway.search.searcher

import com.google.common.collect.ImmutableList
import kotlinx.coroutines.reactor.mono
import org.heeheepresso.gateway.common.response.StatusCode
import org.heeheepresso.gateway.search.SearchResult
import org.heeheepresso.gateway.search.request.SearchRequest
import org.heeheepresso.gateway.search.request.SearchRequestHandler
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EventSearcher: Searcher {
    override suspend fun search(request: SearchRequest): Mono<SearchResult> {
        return mono {
            SearchResult(
                statusCode = StatusCode.SUCCESS,
                searcherType = SearcherType.EVENT,
                searchRequestHandler = SearchRequestHandler.HOME,
                imageUrls = ImmutableList.of(
                    "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349",
                    "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349",
                    "https://github.com/HeeHeePresso/Backend/assets/49651099/7febfe0c-4aa9-47c1-8e74-cac555327349")
            )
        }
    }
}