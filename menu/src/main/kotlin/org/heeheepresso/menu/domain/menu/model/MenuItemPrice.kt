package org.heeheepresso.menu.domain.menu.model

import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
data class MenuItemPrice(
    var storePrice: BigDecimal,
    var salePrice : BigDecimal,
    var takeOutPrice: BigDecimal,
) {
}