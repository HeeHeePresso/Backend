package org.heeheepresso.gateway.search

import org.heeheepresso.gateway.context.ContextElaborator
import org.heeheepresso.gateway.processor.post.PostProcessor
import org.heeheepresso.gateway.search.query.SearchQuery

data class SearchContext(
    private var userInfo: UserInfo,
    val searchRequestType: SearchRequestType,
    val contextElaborators: List<ContextElaborator>,
    val searchQueries: List<SearchQuery>,
    val postProcessors: List<PostProcessor>,
) {
    fun decorateUserInfo(userId: Long, storeId: Long) {
        this.userInfo = UserInfo(userId = userId, storeId = storeId)
    }

    fun getUserInfo(): UserInfo {
        return this.userInfo
    }
}
