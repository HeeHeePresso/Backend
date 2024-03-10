package org.heeheepresso.gateway.menu.category

import com.google.common.collect.ImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.heeheepresso.gateway.common.Context
import org.heeheepresso.gateway.menu.category.dto.MenuCategoryList
import org.heeheepresso.gateway.menu.category.dto.RecommendedCarousel
import org.heeheepresso.gateway.menu.category.dto.RecommendedPageResponse
import org.heeheepresso.gateway.menu.domain.MenuDetail
import org.heeheepresso.gateway.menu.menuDetail.MenuDetailService
import org.heeheepresso.gateway.recommendation.RecommendationHandler
import org.heeheepresso.gateway.recommendation.RecommendationHandler.*
import org.heeheepresso.gateway.recommendation.RecommendationResultSet
import org.heeheepresso.gateway.recommendation.RecommendationService
import org.heeheepresso.gateway.user.UserService
import org.springframework.stereotype.Service

@Service
class MenuCategoryService(
        private val userService: UserService,
        private val recommendationService: RecommendationService,
        private val menuDetailService: MenuDetailService,
) {

    companion object {
        private val RECOMMENDED_PAGE_HANDLERS =
                ImmutableList.of<RecommendationHandler>(NEWLY_RELEASED, HIGHLY_RECOMMENDED, BREAD)
        private val MENU_CATEGORY_HANDLER = MENU_CATEGORY
    }

    suspend fun getRecommendedPage(userId: Long): RecommendedPageResponse {
        return coroutineScope {
            val storeId = async { userService.getStore(userId) }
            val resultSet = recommendationService
                    .getRecommendedMenus(Context(userId = userId, storeId = storeId.await(), null), RECOMMENDED_PAGE_HANDLERS)

            val menuDetailMap = async {
                menuDetailService
                        .getMenuDetails(resultSet.getTotalMenuIds()).associateBy { it.menuId }
            }

            buildRecommendedPageResponse(resultSet, menuDetailMap.await())
        }
    }

    private fun buildRecommendedPageResponse(
            resultSet: RecommendationResultSet, menuDetails: Map<Long, MenuDetail>
    ): RecommendedPageResponse {
        val recommendedCarousels = resultSet.results
                .map { RecommendedCarousel(it.handler, it.getMenus(menuDetails)) }
        return RecommendedPageResponse(recommendedCarousels)
    }

    suspend fun getPageByCategory(userId: Long, category: String): MenuCategoryList {
        return coroutineScope {
            val storeId = async { userService.getStore(userId) }
            val resultSet = recommendationService
                .getRecommendedMenus(Context(userId = userId, storeId = storeId.await(), MenuCategory.valueOf(category)), ImmutableList.of<RecommendationHandler>(
                    MENU_CATEGORY_HANDLER))

            val menuDetailMap = async {
                menuDetailService
                    .getMenuDetails(resultSet.getTotalMenuIds()).associateBy { it.menuId }
            }

            MenuCategoryList(resultSet.getMenuList(MENU_CATEGORY_HANDLER, menuDetailMap.await()))
        }
    }
}