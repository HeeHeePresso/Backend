package org.heeheepresso.menu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MenuApplication

fun main(args: Array<String>) {
    runApplication<MenuApplication>(*args)
}
