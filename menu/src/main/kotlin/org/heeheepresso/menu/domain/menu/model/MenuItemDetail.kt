package org.heeheepresso.menu.domain.menu.model

import jakarta.persistence.Embeddable

@Embeddable
data class MenuItemDetail(
    var temperature: Temperature,
    var thumbnailUrl: String,

) {
}