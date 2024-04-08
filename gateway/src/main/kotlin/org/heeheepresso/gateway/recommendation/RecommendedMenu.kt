package org.heeheepresso.gateway.recommendation

import org.heeheepresso.gateway.menu.domain.MenuInfo

data class RecommendedMenu(
        val menuId: Long,
) {
    fun toMenuInfoFromMap(menuInfoMap: Map<Long, MenuInfo>): MenuInfo {
        val menuInfo = menuInfoMap[this.menuId]
        return MenuInfo(
                menuId = this.menuId,
                price = menuInfo!!.price,
                name = menuInfo.name,
                category = menuInfo.category,
                imagePath = menuInfo.imagePath,
                subTitle = menuInfo.subTitle
        )
    }
}

