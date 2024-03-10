package org.heeheepresso.menu.interfaces.dto

import org.heeheepresso.menu.domain.menu.model.MenuStatus

data class ModifyStatusRequest (
    var status: MenuStatus
        ){
}