package org.heeheepresso.gateway.recommendation

import com.google.common.collect.ImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.heeheepresso.gateway.common.Context
import org.heeheepresso.gateway.menu.category.MenuCategory
import org.springframework.stereotype.Service

@Service
class RecommendationService {

    companion object {
        private const val CAROUSEL_PAGE_SIZE = 9
    }

    suspend fun getRecommendedMenus(
            context: Context, handlers: ImmutableList<RecommendationHandler>): RecommendationResultSet {
        val resultSet: ArrayList<RecommendationResult> = ArrayList()
        coroutineScope {
            handlers.map {
                val request = RecommendedRequest(
                        handler = it.name,
                        menuCategory = null, // 정해진 카테고리가 없으므로 null 처리
                        userId = context.userId,
                        storeId = context.storeId,
                        pageSize = CAROUSEL_PAGE_SIZE,
                        offset = 0,
                )
                async { getRecommendedMenu(request) }
            }.forEach {
                resultSet.add(it.await())
            }
        }
        return RecommendationResultSet(resultSet)
    }

    private suspend fun getRecommendedMenu(request: RecommendedRequest): RecommendationResult {
        return RecommendationResult(
                recommendedMenus = ImmutableList.of(RecommendedMenu(1)),
                handler = request.handler)
    }

    suspend fun getMenuByCategory(context: Context, menuCategory: MenuCategory): RecommendationResult {
        return coroutineScope {
            async {
                getRecommendedMenu(RecommendedRequest(
                        handler = RecommendationHandler.MENU_CATEGORY.name, // 카테고리 메뉴에 대한 요청
                        menuCategory = menuCategory,
                        userId = context.userId,
                        storeId = context.storeId,
                        pageSize = CAROUSEL_PAGE_SIZE,
                        offset = 0,
                ))
            }.await()
        }
    }
}
