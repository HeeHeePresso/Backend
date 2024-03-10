package org.heeheepresso.gateway.recommendation

import org.heeheepresso.gateway.menu.category.MenuCategory

data class RecommendedMenu(
    val menuId: Long,
    var menuCategory: MenuCategory,
    var imagePath: String,
    var title: String,
    var subTitle: String,
    var price: Long,
) {
    constructor(menuId: Long) : this(1L, MenuCategory.SET_MENU, "", "", "", 0L)
}
