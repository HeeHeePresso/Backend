package org.heeheepresso.gateway.recommendation

import org.heeheepresso.gateway.menu.category.MenuCategory

data class RecommendedRequest(
    val handler: String,
    val menuCategory: MenuCategory?,
    val userId: Long,
    val storeId: Long,
    val pageSize: Int,
    val offset: Int,
)
