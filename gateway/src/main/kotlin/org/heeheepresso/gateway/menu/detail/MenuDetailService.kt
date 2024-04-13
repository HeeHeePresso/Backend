package org.heeheepresso.gateway.menu.detail

import com.google.common.collect.ImmutableList
import org.heeheepresso.gateway.menu.category.MenuCategory
import org.heeheepresso.gateway.menu.domain.MenuInfo
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MenuDetailService {

    @Cacheable("menuDetail")
    suspend fun getMenuDetails(menuIds: List<Long>?): List<MenuInfo> {
        return ImmutableList.of(
                MenuInfo(
                        menuId = 1L,
                        name = "신승건",
                        price = 1000,
                        category = MenuCategory.TEA_AND_ADE,
                        imagePath = "/sample.png",
                        subTitle = "ssg",
                ),
                MenuInfo(
                        menuId = 2L,
                        name = "김희재",
                        price = 2000,
                        category = MenuCategory.COFFEE,
                        imagePath = "/sample2.png",
                        subTitle = "khj",
                ),
                MenuInfo(
                        menuId = 3L,
                        name = "테스트",
                        price = 4500,
                        category = MenuCategory.TEA_AND_ADE,
                        imagePath = "/sample3.png",
                        subTitle = "test",
                ),
        )
    }
}