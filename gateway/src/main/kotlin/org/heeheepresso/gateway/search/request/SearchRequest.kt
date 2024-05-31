package org.heeheepresso.gateway.search.request

import org.heeheepresso.gateway.menu.category.MenuCategory

data class SearchRequest(
    val userId: Long,
    val handler: SearchRequestHandler,
    val storeId: Long,
    val pageSize: Int,
    val offset: Int,
    val category: MenuCategory?
) {
    constructor(userId: Long, handler: SearchRequestHandler, pageSize: Int):
        this(userId = userId, handler = handler, storeId = 0, pageSize = pageSize, offset = 0, category = null)

    constructor(userId: Long, handler: SearchRequestHandler, pageSize: Int, category: MenuCategory?):
            this(userId = userId, handler = handler, storeId = 0, pageSize = pageSize, offset = 0, category = category)

}
