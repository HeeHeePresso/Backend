package org.heeheepresso.gateway.common

import org.heeheepresso.gateway.menu.category.MenuCategory
import org.heeheepresso.gateway.menu.moreinfo.MoreInfo
import org.heeheepresso.gateway.recommendation.RecommendationHandler

data class Context(
    val userId: Long,
    val storeId: Long,
    val menuCategory: MenuCategory?,
    val handlers: List<RecommendationHandler>,
    val moreInfos: List<MoreInfo>?
)
