package org.heeheepresso.gateway.menu.domain

import org.heeheepresso.gateway.menu.category.MenuCategory

data class MenuDetail(
        val menuId: Long,
        val category: MenuCategory,
        val imagePath: String,
        val title: String,
        val subTitle: String,
        val price: Long,
) {
    constructor() : this(0, MenuCategory.COFFEE, "", "", "", 0)
}
