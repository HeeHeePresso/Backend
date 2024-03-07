package org.heeheepresso.menu.domain.store.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Store(
        @Id
        @GeneratedValue
        var id: Long?,
        var name: String,
        var thumbnailUrl: String,
        var address: String,
)
