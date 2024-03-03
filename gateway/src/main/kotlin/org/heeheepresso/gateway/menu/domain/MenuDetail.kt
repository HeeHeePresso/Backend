package org.heeheepresso.gateway.menu.domain

data class MenuDetail(
    val menuId: Long,
    val imagePath: String,
    val nameKr: String,
    val nameEng: String,
    val price: Long,
) {
    constructor(): this(0, "", "", "", 0)
}
