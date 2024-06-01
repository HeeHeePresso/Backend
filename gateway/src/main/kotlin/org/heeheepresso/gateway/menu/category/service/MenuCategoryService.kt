package org.heeheepresso.gateway.menu.category.service

import com.google.common.collect.ImmutableList
import org.heeheepresso.gateway.context.UserInfoContextElaborator
import org.heeheepresso.gateway.home.MenuResult
import org.heeheepresso.gateway.menu.category.CategoryMenusResponse
import org.heeheepresso.gateway.menu.category.MenuCategory
import org.heeheepresso.gateway.menu.domain.MenuBase
import org.heeheepresso.gateway.processor.post.MenuDetailSearchProcessor
import org.heeheepresso.gateway.search.*
import org.heeheepresso.gateway.search.query.MenuCategoryRecommendationSearchQuery
import org.heeheepresso.gateway.search.request.SearchRequestHandler
import org.heeheepresso.gateway.search.searcher.SearcherType
import org.springframework.stereotype.Service

@Service
class MenuCategoryService(
        private val searcherService: SearcherService,
        private val userInfoContextElaborator: UserInfoContextElaborator,
        private val menuDetailSearchProcessor: MenuDetailSearchProcessor,
        private val menuCategoryRecommendationSearchQuery: MenuCategoryRecommendationSearchQuery
) {

    suspend fun getMenusByCategory(menuCategory: MenuCategory, userId: Long): CategoryMenusResponse {
        val response = searcherService.search(buildSearchContext(userId, menuCategory))

        return buildResponse(category = menuCategory, response = response)
    }

    private fun buildSearchContext(userId: Long, category: MenuCategory): SearchContext {
        return SearchContext(
                UserInfo(userId = userId),
                searchRequestType = SearchRequestType.HOME,
                contextElaborators = ImmutableList.of(userInfoContextElaborator),
                searchQueries = ImmutableList.of(menuCategoryRecommendationSearchQuery),
                postProcessors = ImmutableList.of(menuDetailSearchProcessor),
                category = category
        )
    }

    private fun buildResponse(category: MenuCategory, response: SearchResponse): CategoryMenusResponse {
        return CategoryMenusResponse(category, getMenuResult(response))
    }

    private fun getMenuResult(response: SearchResponse): List<MenuResult> {
        val menuBases = response.getResultBy<MenuBase>(searcherType = SearcherType.RECOMMENDATION, searchRequestHandler = SearchRequestHandler.MENU_CATEGORY)
        return ImmutableList.of(MenuResult(SearchRequestHandler.HOME.name, menuBases))
    }
}