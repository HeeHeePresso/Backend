package org.heeheepresso.gateway.config.webclient

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
class WebClientConfig {
    companion object {
        private const val TIMEOUT_MILLIS = 5000L
    }

    @Bean
    @LoadBalanced
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }
}