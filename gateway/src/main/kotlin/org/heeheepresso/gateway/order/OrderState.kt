package org.heeheepresso.gateway.order

import org.heeheepresso.gateway.user.UserRole

enum class OrderState {
    PAID,
    CHECKED,
    WAITING,
    RECEIVED,
    CANCELED, // by customer
    REJECTED; // by employee
    companion object{
        fun isAbleToChangeByRole(userRole: UserRole, orderState: OrderState): Boolean{
            return when(userRole){
                UserRole.CUSTOMER -> {
                    when(orderState){
                        CANCELED -> true
                        else -> false
                    }
                }
                else -> true
            }
        }
    }

}