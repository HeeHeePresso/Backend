package org.heeheepresso.gateway.processor.post

import org.heeheepresso.gateway.search.SearchResponse

interface PostProcessor {

    suspend fun process(response: SearchResponse): List<Any>
}