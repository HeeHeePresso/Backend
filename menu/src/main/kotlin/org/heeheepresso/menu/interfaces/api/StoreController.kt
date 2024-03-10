package org.heeheepresso.menu.interfaces.api

import org.heeheepresso.menu.domain.store.model.Store
import org.heeheepresso.menu.domain.store.service.StoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StoreController (
        val storeService: StoreService
) {

    @GetMapping("/stores")
    fun getAllStores(): List<Store> {
        return storeService.findAll()
    }

    @PostMapping("/stores")
    fun createStore(@RequestBody store: Store) {
        storeService.save(store)
    }
}