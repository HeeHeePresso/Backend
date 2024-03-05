package org.heeheepresso.gateway.menu.domain

data class MenuDetail(
    val menuId: Long,
    val imagePath: String,
    val title: String,
    val subTitle: String,
    val price: Long,
) {
    constructor(): this(0, "", "", "", 0)
}
