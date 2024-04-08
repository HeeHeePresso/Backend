package org.heeheepresso.gateway.menu.domain

import org.heeheepresso.gateway.menu.category.MenuCategory

data class MenuInfo(
        val menuId: Long,
        var price: Long,
        var name: String,
        var category: MenuCategory,
        var imagePath: String,
        var subTitle: String,
) {
    constructor(menuId: Long) : this(menuId, 0L, "", MenuCategory.SET_MENU, "", "")

    fun getBaseInfo(): MenuBase {
        return MenuBase(
                this.menuId,
                this.name,
                this.price,
                this.imagePath
        )
    }

}
