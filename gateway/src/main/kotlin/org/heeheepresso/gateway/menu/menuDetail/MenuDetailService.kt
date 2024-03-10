package org.heeheepresso.gateway.menu.menuDetail

import com.google.common.collect.ImmutableList
import org.heeheepresso.gateway.menu.category.MenuCategory
import org.heeheepresso.gateway.menu.domain.MenuDetail
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MenuDetailService {

    @Cacheable("menuDetail")
    suspend fun getMenuDetails(menuIds: List<Long>): List<MenuDetail> {
        return ImmutableList.of(MenuDetail(
                menuId = 1L,
                category = MenuCategory.TEA_AND_ADE,
                imagePath = "/sample.png",
                title = "신승건",
                subTitle = "ssg",
                price = 1000,
        ), MenuDetail(
                menuId = 2L,
                category = MenuCategory.COFFEE,
                imagePath = "/sample2.png",
                title = "김희재",
                subTitle = "khj",
                price = 2000)
        )
    }
}