package org.heeheepresso.gateway.menu.moreinfo

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.heeheepresso.gateway.menu.domain.MenuDetail
import org.heeheepresso.gateway.menu.menuDetail.MenuDetailService
import org.heeheepresso.gateway.recommendation.RecommendationResult
import org.heeheepresso.gateway.recommendation.RecommendedMenu
import org.springframework.stereotype.Component

@Component
class MenuDetailInfo(
    val menuDetailService: MenuDetailService
) : MoreInfo {
    override suspend fun addMoreInfo(resultSet: List<RecommendationResult>) {
        coroutineScope {
            val menuDetailMap = async {
                menuDetailService
                    .getMenuDetails(getTotalMenuIds(resultSet)).associateBy { it.menuId }
            }.await()

            resultSet
                .flatMap { it.recommendedMenus }
                .forEach { addMenuDetailInfo(menuDetailMap, it) }
        }
    }

    private fun addMenuDetailInfo(
        menuDetailMap: Map<Long, MenuDetail>,
        it: RecommendedMenu
    ) {
        val menuDetail = menuDetailMap[it.menuId]
        it.menuCategory = menuDetail!!.category
        it.imagePath = menuDetail.imagePath
        it.title = menuDetail.title
        it.subTitle = menuDetail.subTitle
        it.price = menuDetail.price
    }

    private fun getTotalMenuIds(results: List<RecommendationResult>): List<Long> {
        return results
            .flatMap { it.recommendedMenus }
            .map { it.menuId }
    }
}