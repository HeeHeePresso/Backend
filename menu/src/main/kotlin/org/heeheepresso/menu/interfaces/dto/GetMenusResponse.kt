package org.heeheepresso.menu.interfaces.dto

import org.heeheepresso.menu.domain.menu.model.Menu
import java.math.RoundingMode

data class GetMenusResponse(
        var id: Long,
        var name: String,
        var price: String,
        var thumbnailImageUrl: String,
) {
    companion object {
        fun from(menu: Menu): GetMenusResponse {
            return GetMenusResponse(
                    menu.menuId!!,
                    menu.title,
                    menu.menuItemPrice.storePrice.setScale(0, RoundingMode.DOWN).toString() + "원",
                    menu.menuItemDetail.thumbnailUrl
            )
        }
    }
}