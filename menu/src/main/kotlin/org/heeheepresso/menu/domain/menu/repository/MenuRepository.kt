package org.heeheepresso.menu.domain.menu.repository

import org.heeheepresso.menu.domain.menu.model.Menu
import org.springframework.data.repository.CrudRepository

interface MenuRepository : CrudRepository<Menu, Long> {
}