package org.heeheepresso.gateway.search

import org.heeheepresso.gateway.search.request.SearchRequestHandler
import org.heeheepresso.gateway.search.searcher.SearcherType

data class SearchResponse(
        val results: List<SearchResult>,
) {

    fun getTotalMenuIds(): List<Long> {
        return results.first { it.searcherType == SearcherType.RECOMMENDATION }
                .searched?.filterIsInstance<Long>()
                ?: emptyList()
    }

    inline fun <reified T> getResultBy(searcherType: SearcherType, searchRequestHandler: SearchRequestHandler): List<T> {
        val result = this.results.filter { it.searcherType == searcherType }
                .firstOrNull { it.searchRequestHandler == searchRequestHandler }
        return result?.searched?.filterIsInstance<T>() ?: emptyList()
    }
}