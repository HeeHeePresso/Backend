package org.heeheepresso.gateway.menu.domain

import org.heeheepresso.gateway.menu.category.MenuCategory

data class MenuInfo(
        val id: Long,
        var price: String,
        var name: String,
        var category: MenuCategory,
        var thumbnailImageUrl: String,
        var subTitle: String,
) {
    constructor(menuId: Long) : this(menuId, "0원", "", MenuCategory.SET_MENU, "", "")

    fun getBaseInfo(): MenuBase {
        return MenuBase(
                this.id,
                this.name,
                this.price,
                this.thumbnailImageUrl
        )
    }

}
