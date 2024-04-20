package org.heeheepresso.paymentapi.kakao.config

import io.github.oshai.kotlinlogging.KotlinLogging
import io.netty.channel.ChannelOption
import io.netty.handler.codec.http.HttpHeaderValues
import org.heeheepresso.paymentapi.common.ApiException
import org.heeheepresso.paymentapi.common.ApiStatus
import org.heeheepresso.paymentapi.kakao.dto.response.KakaoPayErrorResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.time.Duration

private val logger = KotlinLogging.logger {}

@Configuration
class KaKaoWebClientConfig(
    private val kakaoApiProperties: KakaoApiProperties,
) {

    @Bean
    fun kakaoPayWebClient(): WebClient {
        // Memory 조정: 2M (default 256KB)
        val exchangeStrategies = ExchangeStrategies.builder()
            .codecs { configurer: ClientCodecConfigurer ->
                configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)
            }
            .build()

        val httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(8000))

        return WebClient.builder()
            .baseUrl(kakaoApiProperties.url.base)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
            .defaultHeader(HttpHeaders.ACCEPT, HttpHeaderValues.APPLICATION_JSON.toString())
            .defaultHeader("Authorization", kakaoApiProperties.authorization)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .exchangeStrategies(exchangeStrategies)
            .filter(ExchangeFilterFunction.ofResponseProcessor { response -> this.is4xx5xxResponseProcessor(response) })
            .build()
    }

    private fun is4xx5xxResponseProcessor(response: ClientResponse): Mono<ClientResponse> {
        logger.info { response.toString() }
        val statusCode = response.statusCode()
        return when {
            statusCode.is4xxClientError -> {
                response.bodyToMono(KakaoPayErrorResponse::class.java).flatMap {
                    Mono.error(ApiException(KakaoApiStatus(it.errorCode, it.errorMessage)))
                }
            }
            statusCode.is5xxServerError -> {
                response.bodyToMono(KakaoPayErrorResponse::class.java).flatMap {
                    Mono.error(ApiException(ApiStatus.KAKAO_PAY_SERVER_ERROR))
                }
            }
            else -> Mono.just(response)
        }
    }
}