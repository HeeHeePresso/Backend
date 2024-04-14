package org.heeheepresso.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
@EnableCaching
@EnableJpaAuditing
class GatewayApplication

fun main(args: Array<String>) {
	runApplication<GatewayApplication>(*args)
}
