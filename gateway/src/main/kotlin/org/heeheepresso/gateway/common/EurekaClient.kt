package org.heeheepresso.gateway.common

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class EurekaClient(
        private val webClientBuilder: WebClient.Builder
) {
    companion object {
        private const val EUREKA_URL = "http://localhost:8761/eureka"
    }

    suspend fun discoverServiceByName(serviceName: String): String {
        discoveryClient(serviceName)
                .get()
                .retrieve()
                .bodyToMono(String::class.java)
                .map { parseServiceUrl(it) }
                .block()?.let {
                    return it
                }
        throw IllegalStateException("존재하지 않는 서비스입니다.")
    }

    private fun discoveryClient(serviceName: String): WebClient {
        return this.webClientBuilder
                .baseUrl("$EUREKA_URL/apps/$serviceName")
                .build()
    }

    private fun parseServiceUrl(xml: String): String? {
        val startTag = "<homePageUrl>"
        val endTag = "/</homePageUrl>"

        val startIndex = xml.indexOf(startTag)
        val endIndex = xml.indexOf(endTag, startIndex)

        if (startIndex == -1 || endIndex == -1)
            return null

        return xml.substring(startIndex + startTag.length, endIndex)
    }
}