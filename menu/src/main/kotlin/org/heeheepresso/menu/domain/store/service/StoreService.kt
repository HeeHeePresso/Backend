package org.heeheepresso.menu.domain.store.service

import org.heeheepresso.menu.domain.store.model.Store
import org.heeheepresso.menu.domain.store.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService (
        val storeRepository: StoreRepository
) {
    fun save(input: Store): Store {
        return storeRepository.save(input)
    }

    fun findById(id: Long): Store {
        return storeRepository.findById(id).orElseThrow()
    }

    fun findAll(): List<Store> {
        return storeRepository.findAll().toList()
    }

    fun deleteAll() {
        storeRepository.deleteAll()
    }

}
