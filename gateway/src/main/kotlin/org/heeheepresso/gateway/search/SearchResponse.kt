package org.heeheepresso.gateway.search

import org.heeheepresso.gateway.search.request.SearchRequestHandler
import org.heeheepresso.gateway.search.searcher.SearcherType

data class SearchResponse(
        val results: List<SearchResult>,
//    val extra: MutableMap<String, List<Any>> = HashMap(),
) {
    companion object {
        const val MENU_DETAIL_IDS = "menuDetailIds"
    }

    fun getTotalMenuIds(): List<Long> {
        return results.mapNotNull { it.searched }.filterIsInstance<List<Long>>().flatten()
    }

    inline fun <reified T> getResultBy(searcherType: SearcherType, searchRequestHandler: SearchRequestHandler): List<T> {
        val result = this.results
                .filter { it.searcherType == searcherType }
                .firstOrNull { it.searchRequestHandler == searchRequestHandler }
        return result?.searched?.filterIsInstance<T>() ?: emptyList()
    }
}