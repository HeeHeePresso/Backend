package org.heeheepresso.gateway.home

import com.google.common.collect.ImmutableList
import org.heeheepresso.gateway.context.UserInfoContextElaborator
import org.heeheepresso.gateway.menu.domain.MenuBase
import org.heeheepresso.gateway.menu.domain.MenuInfo
import org.heeheepresso.gateway.processor.post.MenuDetailSearchProcessor
import org.heeheepresso.gateway.search.*
import org.heeheepresso.gateway.search.SearchResponse.Companion.MENU_DETAIL_IDS
import org.heeheepresso.gateway.search.query.EventSearchQuery
import org.heeheepresso.gateway.search.query.HomeRecommendationSearchQuery
import org.heeheepresso.gateway.search.request.SearchRequestHandler.HOME
import org.heeheepresso.gateway.search.searcher.SearcherType.EVENT
import org.heeheepresso.gateway.search.searcher.SearcherType.RECOMMENDATION
import org.springframework.stereotype.Service

@Service
class HomeService(
        private val searcherService: SearcherService,
        private val userInfoContextElaborator: UserInfoContextElaborator,
        private val menuDetailSearchProcessor: MenuDetailSearchProcessor,
        private val eventSearchQuery: EventSearchQuery,
        private val homeRecommendationSearchQuery: HomeRecommendationSearchQuery,
) {
    suspend fun getHomeData(userId: Long): HomePageResponse {
        val response = searcherService.search(buildSearchContext(userId))
        return buildHomePageResponse(response)
    }

    private fun buildSearchContext(userId: Long): SearchContext {
        return SearchContext(
                UserInfo(userId = userId),
                searchRequestType = SearchRequestType.HOME,
                contextElaborators = ImmutableList.of(userInfoContextElaborator),
                searchQueries = ImmutableList.of(eventSearchQuery, homeRecommendationSearchQuery),
                postProcessors = ImmutableList.of(menuDetailSearchProcessor)
        )
    }

    private fun buildHomePageResponse(response: SearchResponse): HomePageResponse {
        return HomePageResponse(getEventUrls(response), getMenuResult(response))
    }

    private fun getEventUrls(response: SearchResponse): List<String> {
//        val result = response.results
//            .filter { it.searcherType == EVENT }
//            .firstOrNull { it.searchRequestHandler == HOME }
//        if (result?.imageUrls == null) {
//            return emptyList()
//        }
        return response.getResultBy<String>(searcherType = EVENT, searchRequestHandler = HOME)
//        return result.imageUrls
    }

    private fun getMenuResult(response: SearchResponse): List<MenuResult> {
//        val menuDetailMap = response.extra.getOrDefault(MENU_DETAIL_IDS, emptyList())
//            .filterIsInstance<MenuInfo>()
//            .associateBy { it.id }
//        if (menuDetailMap.isEmpty()) {
//            return emptyList()
//        }
//
//        val results = response.results
//            .filter { it.searcherType == RECOMMENDATION }
//            .firstOrNull { it.searchRequestHandler == HOME }
//        if (results?.menuIds == null) {
//            return emptyList()
//        }

//        val menuBaseList =
//            results.menuIds.map { menuDetailMap.getOrDefault(it, MenuInfo(it)) }
//            .map {
//                MenuBase(
//                    id = it.id,
//                    name = it.name,
//                    price = it.price,
//                    thumbnailImageUrl = it.thumbnailImageUrl
//                )
//            }
        val menuBases = response.getResultBy<MenuBase>(searcherType = RECOMMENDATION, searchRequestHandler = HOME)
        return ImmutableList.of(MenuResult(HOME.name, menuBases))
    }
}