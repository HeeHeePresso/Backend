package org.heeheepresso.gateway.user.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "USER_ID")
        val id: Long,
        @Column(unique = true, nullable = false)
        val phoneNumber: String,
        @Column(length = 15, nullable = false)
        val username: String,
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val userRole: UserRole,
        @CreatedDate
        @Column(updatable = false, nullable = false)
        val registerDate: LocalDateTime
)
