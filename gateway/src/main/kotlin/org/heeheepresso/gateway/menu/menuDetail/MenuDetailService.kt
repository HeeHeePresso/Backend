package org.heeheepresso.gateway.menu.menuDetail

import com.google.common.collect.ImmutableList
import org.heeheepresso.gateway.menu.domain.MenuDetail
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MenuDetailService {

    @Cacheable("menuDetail")
    suspend fun getMenuDetails(menuIds: List<Long>): List<MenuDetail> {
        return ImmutableList.of(MenuDetail(
            menuId = 1L,
            imagePath = "/sample.png",
            title = "신승건",
            subTitle = "ssg",
            price = 1000,
        ))
    }
}