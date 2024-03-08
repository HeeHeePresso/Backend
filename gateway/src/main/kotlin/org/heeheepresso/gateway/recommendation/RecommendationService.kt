package org.heeheepresso.gateway.recommendation

import com.google.common.collect.ImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.glassfish.jersey.internal.guava.Lists
import org.heeheepresso.gateway.common.Context
import org.heeheepresso.gateway.menu.category.MenuCategory
import org.heeheepresso.gateway.menu.domain.Menu
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

    suspend fun getRecommendedMenuByCategory(context: Context, menuCategory: MenuCategory): RecommendationResult {
        return coroutineScope {
            async {
                getRecommendedMenu(RecommendedRequest(
                        handler = menuCategory.name,
                        userId = context.userId,
                        storeId = context.storeId,
                        pageSize = CAROUSEL_PAGE_SIZE,
                        offset = 0,
                ))
            }.await()
        }
    }
}
