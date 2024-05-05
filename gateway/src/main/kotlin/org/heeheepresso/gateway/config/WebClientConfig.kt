package org.heeheepresso.gateway.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit


@Configuration
class WebClientConfig(
) {
    companion object {
        private const val TIMEOUT_MILLIS = 5000L
        private const val MENU_SERVICE_URL = "http://localhost:8081"
        private const val RECOMMEND_SERVICE_URL = "http://localhost:8082"
        private const val ORDER_SERVICE_URL = "http://localhost:8083"
    }

    @Bean
    fun menuWebClient(): WebClient {
        return WebClient.builder()
                .clientConnector(ReactorClientHttpConnector(this.httpClient()))
                .baseUrl(MENU_SERVICE_URL)
                .exchangeStrategies(this.exchangeStrategies())
                .build()
    }

    @Bean
    fun recommendWebClient(): WebClient {
        return WebClient.builder()
                .clientConnector(ReactorClientHttpConnector(this.httpClient()))
                .baseUrl(RECOMMEND_SERVICE_URL)
                .exchangeStrategies(this.exchangeStrategies())
                .build()
    }

    private fun httpClient() = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT_MILLIS.toInt())
            .responseTimeout(Duration.ofMillis(TIMEOUT_MILLIS))
            .doOnConnected {
                it.addHandlerLast(ReadTimeoutHandler(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
                        .addHandlerLast(WriteTimeoutHandler(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
            }

    private fun exchangeStrategies() = ExchangeStrategies.builder()
            .codecs {
                it.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)
            }.build()
}