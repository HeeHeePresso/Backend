package org.heeheepresso.menu.domain.store.repository

import org.heeheepresso.menu.domain.store.model.Store
import org.springframework.data.repository.CrudRepository

interface StoreRepository : CrudRepository<Store, Long> {

}
