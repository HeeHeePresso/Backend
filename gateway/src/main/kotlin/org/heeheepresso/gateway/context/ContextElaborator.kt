package org.heeheepresso.gateway.context

import org.heeheepresso.gateway.search.SearchContext

interface ContextElaborator {

    suspend fun elaborate(searchContext: SearchContext)
}