package org.heeheepresso.gateway.config

enum class CacheType(
    val cacheName: String,
    val expireAfterWrite: Long,
    val maximumSize: Long,
) {
    MENU_DETAIL("menuDetail", 10, 100)
}