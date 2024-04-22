package org.heeheepresso.gateway.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.responses.ApiResponse
import org.springdoc.core.customizers.OpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@Configuration
class SwaggerConfig {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
                .components(Components())
                .info(this.apiInfo())
        // TODO: 시큐리티 처리 및 공통 에러 응답 처리; https://akku-dev.tistory.com/158
    }

    private fun apiInfo() = Info()
            .title("HeeHeePresso API")
            .description("HeeHeePresso Gateway API Documents")
            .version("1.0.0")

    @Bean
    fun customResponses() = OpenApiCustomizer { openApi ->
        openApi.paths.orEmpty()
                .values
                .forEach { pathItem ->
                    pathItem.readOperations()
                            .forEach { operation ->
                                val apiResponses = operation.responses

                                apiResponses.addApiResponse("400", createErrorResponse("400", "Bad Request"))
                                apiResponses.addApiResponse("500", createErrorResponse("500", "Internal Server Error"))
                            }
                }
    }

    private fun createErrorResponse(code: String, desc: String): ApiResponse {
        val exception = when (code) {
            "400" -> """
                {
                    "code" : "400",
                    "message" : "잘못된 요청입니다.",
                    "timestamp" : "2024-04-22T11:02Z"
                }
            """.trimIndent()

            "500" -> """
                {
                    "code" : "500",
                    "message" : "서버에서 오류가 발생했습니다.",
                    "timestamp" : "2024-04-22T11:02Z"
                }
            """.trimIndent()

            else -> ""
        }
        return createResponse(exception, desc)
    }

    private fun createResponse(form: String, desc: String): ApiResponse {
        val example = Example().apply {
            value = form
        }
        val mediaType = MediaType().apply {
            addExamples("example", example)
            schema = Schema<Any>()
        }

        val content = Content().apply {
            addMediaType(APPLICATION_JSON_VALUE, mediaType)
        }

        return ApiResponse().apply {
            this.description = desc
            this.content = content
        }
    }
}