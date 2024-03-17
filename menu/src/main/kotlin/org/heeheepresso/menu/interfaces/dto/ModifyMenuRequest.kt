package org.heeheepresso.menu.interfaces.dto

import jakarta.persistence.Embedded
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import org.heeheepresso.menu.domain.menu.model.*

data class ModifyMenuRequest (
    var title: String,

    var subTitle: String,

    var description: String,

    var category: Category,

    var flagType: FlagType,

    var status: MenuStatus,

    var menuItemDetail: MenuItemDetail,

    var menuItemPrice: MenuItemPrice,
        ){
}