package org.heeheepresso.gateway.recommendation

import org.heeheepresso.gateway.menu.domain.MenuInfo

data class RecommendedMenu(
        val menuId: Long,
) {
    fun toMenuInfoFromMap(menuInfoMap: Map<Long, MenuInfo>): MenuInfo {
        val menuInfo = menuInfoMap[this.menuId]
        return MenuInfo(
                id = this.menuId,
                price = menuInfo!!.price,
                name = menuInfo.name,
                category = menuInfo.category,
                thumbnailImageUrl = menuInfo.thumbnailImageUrl,
                subTitle = menuInfo.subTitle
        )
    }
}

