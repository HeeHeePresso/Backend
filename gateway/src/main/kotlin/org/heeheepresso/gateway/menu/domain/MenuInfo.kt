package org.heeheepresso.gateway.menu.domain

import org.heeheepresso.gateway.menu.category.MenuCategory

data class MenuInfo(
        val id: Long,
        var name: String,
        var price: String,
        var thumbnailImageUrl: String,
        var category: MenuCategory,
        var subTitle: String,
) {
    constructor(menuId: Long) : this(menuId, "", "0원", "", MenuCategory.COFFEE, "")

    fun getBaseInfo(): MenuBase {
        return MenuBase(
                this.id,
                this.name,
                this.price,
                this.thumbnailImageUrl
        )
    }

}
