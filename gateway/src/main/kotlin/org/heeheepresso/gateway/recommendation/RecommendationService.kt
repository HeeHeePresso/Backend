package org.heeheepresso.gateway.recommendation

import com.google.common.collect.ImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.heeheepresso.gateway.common.Context
import org.heeheepresso.gateway.menu.category.RecommendationFilterUtils.Companion.addCategoryFilter
import org.heeheepresso.gateway.menu.moreinfo.MoreInfo
import org.springframework.stereotype.Service

@Service
class RecommendationService(
        private val moreInfos: List<MoreInfo>
) {

    companion object {
        private const val CAROUSEL_PAGE_SIZE = 9
    }

    suspend fun getRecommendedMenus(context: Context): RecommendedResultMenuSet {
        val resultSet: ArrayList<RecommendationResult> = ArrayList()
        val resultMenus = mutableListOf<RecommendedResultMenu>()
        coroutineScope {
            resultSet.addAll(context.handlers
                    .map {
                        async {
                            getRecommendedMenu(
                                    RecommendedRequest(
                                            handler = it.name,
                                            where = context.menuCategory?.let { category -> addCategoryFilter(category) },
                                            userId = context.userId,
                                            storeId = context.storeId,
                                            pageSize = CAROUSEL_PAGE_SIZE,
                                            offset = 0,
                                    )
                            )
                        }.await()
                    })
            moreInfos.forEach {
                it.setMoreInfo(resultSet, resultMenus)
            }
        }
        return RecommendedResultMenuSet(resultMenus.toList())
    }

    private suspend fun getRecommendedMenu(request: RecommendedRequest): RecommendationResult {
        return RecommendationResult(
                recommendedMenus = ImmutableList.of(RecommendedMenu(1), RecommendedMenu(3)),
                handler = if (request.handler == "HOME") "SEASON_RECOMMENDED" else request.handler
        )
    }
}
