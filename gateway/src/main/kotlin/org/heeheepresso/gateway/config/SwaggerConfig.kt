package org.heeheepresso.gateway.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openApi(): OpenAPI{
        return OpenAPI()
                .components(Components())
                .info(this.apiInfo())
        // TODO: 시큐리티 처리 및 공통 에러 응답 처리; https://akku-dev.tistory.com/158
    }

    private fun apiInfo() = Info()
            .title("HeeHeePresso API")
            .description("HeeHeePresso Gateway API Documents")
            .version("1.0.0")
}