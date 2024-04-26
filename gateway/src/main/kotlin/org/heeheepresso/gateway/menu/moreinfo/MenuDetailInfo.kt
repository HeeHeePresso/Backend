package org.heeheepresso.gateway.menu.moreinfo

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.heeheepresso.gateway.menu.detail.MenuDetailService
import org.heeheepresso.gateway.recommendation.RecommendationResult
import org.heeheepresso.gateway.recommendation.RecommendedResultMenu
import org.springframework.stereotype.Component

@Component
class MenuDetailInfo(
        val menuDetailService: MenuDetailService
) : MoreInfo {
    override suspend fun setMoreInfo(resultSet: List<RecommendationResult>, resultMenus: MutableList<RecommendedResultMenu>) {
        coroutineScope {
            val menuDetailMap = async {
                menuDetailService
                        .getMenuDetails(getTotalMenuIds(resultSet)).associateBy { it.id }
            }.await()

            if (menuDetailMap.isNotEmpty()) {
                resultSet.mapTo(resultMenus) { item ->
                    RecommendedResultMenu(item.recommendedMenus
                            .map { it.toMenuInfoFromMap(menuDetailMap) }, item.handler)
                }
            }
        }
    }

    private fun getTotalMenuIds(results: List<RecommendationResult>): List<Long> {
        return results
                .flatMap { it.recommendedMenus }
                .map { it.menuId }
    }
}