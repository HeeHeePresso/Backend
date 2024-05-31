package org.heeheepresso.gateway.search

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import org.heeheepresso.gateway.common.response.StatusCode
import org.heeheepresso.gateway.search.searcher.SearcherGateway
import org.springframework.stereotype.Service

@Service
class SearcherService(
        private val searcherGateway: SearcherGateway,
) {

    suspend fun search(searchContext: SearchContext): SearchResponse {
        elaborateContext(searchContext)
        val response = query(searchContext)
        postProcessAfterSearch(searchContext, response)
        return response
    }

    private suspend fun elaborateContext(searchContext: SearchContext) {
        return coroutineScope {
            searchContext.contextElaborators.forEach {
                it.elaborate(searchContext)
            }
        }
    }

    private suspend fun query(searchContext: SearchContext): SearchResponse {
        return coroutineScope {
            val results = searchContext.searchQueries
                    .map {
                        async { searcherGateway.search(it.getSearcher(), it.buildRequest(searchContext)).awaitSingle() }
                    }.map { it.await() }
                    .filter { it.statusCode == StatusCode.SUCCESS }
            SearchResponse(results)
        }
    }

    private suspend fun postProcessAfterSearch(
            searchContext: SearchContext, response: SearchResponse) {

        return coroutineScope {
            searchContext.postProcessors.forEach {
                response.results.map { result -> result.searched = it.process(response) }
            }
        }
    }
}