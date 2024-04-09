package org.heeheepresso.gateway.menu.category

import com.google.common.collect.ImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.heeheepresso.gateway.common.Context
import org.heeheepresso.gateway.menu.category.dto.MenuCategoryList
import org.heeheepresso.gateway.menu.category.dto.RecommendedCarousel
import org.heeheepresso.gateway.menu.category.dto.RecommendedPageResponse
import org.heeheepresso.gateway.recommendation.*
import org.heeheepresso.gateway.recommendation.RecommendationHandler.*
import org.heeheepresso.gateway.user.UserService
import org.springframework.stereotype.Service

@Service
class MenuCategoryService(
        private val userService: UserService,
        private val recommendationService: RecommendationService,
) {

    companion object {
        private val RECOMMENDED_PAGE_HANDLERS =
                ImmutableList.of<RecommendationHandler>(NEWLY_RELEASED, HIGHLY_RECOMMENDED, BREAD)
        private val MENU_CATEGORY_HANDLER = MENU_CATEGORY
    }

    suspend fun getRecommendedPage(userId: Long): RecommendedPageResponse {
        return coroutineScope {
            val resultSet = recommendationService.getRecommendedMenus(
                    getContext(
                            userId = userId,
                            category = null,
                            handlers = RECOMMENDED_PAGE_HANDLERS,
                    )
            )
            buildRecommendedPageResponse(resultSet)
        }
    }

    private fun buildRecommendedPageResponse(resultSet: RecommendedResultMenuSet): RecommendedPageResponse {
        val recommendedCarousels = resultSet.results
                .map { RecommendedCarousel(it.handler, it.recommendedMenus) }
        return RecommendedPageResponse(recommendedCarousels)
    }

    suspend fun getPageByCategory(userId: Long, category: String): MenuCategoryList {
        return coroutineScope {
            val resultSet = recommendationService.getRecommendedMenus(
                    getContext(
                            userId = userId,
                            category = category,
                            handlers = ImmutableList.of<RecommendationHandler>(MENU_CATEGORY_HANDLER),
                    )
            )
            MenuCategoryList(resultSet.getMenuList(MENU_CATEGORY_HANDLER))
        }
    }

    suspend fun getContext(
            userId: Long, category: String?, handlers: List<RecommendationHandler>?
    ): Context {
        return coroutineScope {
            val storeId = async { userService.getStore(userId) }
            val menuCategory = if (category != null) MenuCategory.valueOf(category) else null
            Context(
                    userId = userId,
                    storeId = storeId.await(),
                    menuCategory = menuCategory,
                    handlers = handlers,
            )
        }
    }
}