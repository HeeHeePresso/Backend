package org.heeheepresso.gateway.common

import org.heeheepresso.gateway.menu.category.MenuCategory

data class Context(
    val userId: Long,
    val storeId: Long,
    val menuCategory: MenuCategory?,
)
