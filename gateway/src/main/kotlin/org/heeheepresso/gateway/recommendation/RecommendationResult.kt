package org.heeheepresso.gateway.recommendation

import org.heeheepresso.gateway.menu.domain.Menu
import org.heeheepresso.gateway.menu.domain.MenuDetail

data class RecommendationResult(
    val recommendedMenus: List<RecommendedMenu>,
    val handler: String,
) {
    fun getMenus(menuDetails: Map<Long, MenuDetail>): List<Menu> {
        return this.recommendedMenus
            .map{ menu -> menuDetails.getOrDefault(menu.menuId, MenuDetail()) }
            .map{ menuDetail -> Menu(
                imagePath = menuDetail.imagePath,
                nameKr = menuDetail.nameKr,
                nameEng = menuDetail.nameEng,
                price = menuDetail.price) }
    }
}
