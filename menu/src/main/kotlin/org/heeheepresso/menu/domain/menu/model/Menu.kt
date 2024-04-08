package org.heeheepresso.menu.domain.menu.model

import jakarta.persistence.*
import org.heeheepresso.menu.common.BaseEntity
import org.heeheepresso.menu.interfaces.dto.ModifyMenuRequest

@Entity
class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val menuId: Long? = null,

    var title: String,

    var subTitle: String,

    var description: String,

    @Enumerated(value = EnumType.STRING)
    var category: Category,

    @Enumerated(value = EnumType.STRING)
    var flagType: FlagType,

    @Enumerated(value = EnumType.STRING)
    var status: MenuStatus,

    @Embedded
    var menuItemDetail: MenuItemDetail,

    @Embedded
    var menuItemPrice: MenuItemPrice,

    ) : BaseEntity() {
    fun modifyStatus(status: MenuStatus) {
        this.status = status
    }

    fun modifyMenu(request: ModifyMenuRequest) {
        this.title = request.title
        this.subTitle = request.subTitle
        this.description = request.description
        this.category = request.category
        this.flagType = request.flagType
        this.status = request.status
        this.menuItemDetail = request.menuItemDetail
        this.menuItemPrice = request.menuItemPrice
    }
}