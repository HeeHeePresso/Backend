package org.heeheepresso.orderapi.orderHistory

import org.heeheepresso.orderapi.orderHistory.dto.response.OrderHistoryCreateResponse
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface OrderHistoryMapper {

    fun convertToResponse(source: OrderHistory): OrderHistoryCreateResponse
}