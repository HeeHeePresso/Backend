package org.heeheepresso.gateway.search

data class SearchResponse(
    val results: List<SearchResult>,
    val extra: MutableMap<String, List<Any>> = HashMap(),
) {
    companion object {
        const val MENU_DETAIL_IDS = "menuDetailIds"
    }

    fun getTotalMenuIds(): List<Long> {
        return results.mapNotNull { it.storeIds }.flatten()
    }
}