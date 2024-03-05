package org.heeheepresso.orderapi.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @Column(name = "created_by", nullable = false, updatable = false)
    @CreatedBy
    var createdBy: String = "system"
        protected set

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.MIN
        protected set

    @Column(name = "modified_by", nullable = false)
    @LastModifiedBy
    var modifiedBy: String = "system"
        protected set

    @LastModifiedDate
    @Column(name = "modified_date", nullable = false)
    var modifiedDate: LocalDateTime = LocalDateTime.MIN
        protected set
}