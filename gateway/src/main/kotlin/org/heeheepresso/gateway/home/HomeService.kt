package org.heeheepresso.gateway.home

import com.google.common.collect.ImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.heeheepresso.gateway.event.EventService
import org.heeheepresso.gateway.menu.category.MenuCategoryService
import org.heeheepresso.gateway.recommendation.RecommendationHandler
import org.heeheepresso.gateway.recommendation.RecommendationHandler.*
import org.heeheepresso.gateway.recommendation.RecommendationService
import org.springframework.stereotype.Service

@Service
class HomeService(
        private val recommendationService: RecommendationService,
        private val menuCategoryService: MenuCategoryService,
        private val eventService: EventService,
) {
    suspend fun getHomeData(userId: Long): HomePageResponse {
        return coroutineScope {
            val recommendedMenus = async {
                recommendationService.getRecommendedMenus(
                        menuCategoryService.getContext(
                                userId = userId,
                                category = null,
                                handlers = ImmutableList.of<RecommendationHandler>(HOME),
                        )
                )
            }
            val menuResults = recommendedMenus.await().mapHandlerWithMenus()
            buildHomePageResponse(eventService.getEventFeatures(), menuResults)
        }
    }

    private fun buildHomePageResponse(events: List<String>, results: List<MenuResult>): HomePageResponse {
        return HomePageResponse(
                eventUrls = events,
                menuInfos = results
        )
    }
}