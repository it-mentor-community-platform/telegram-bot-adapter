package com.itmentorcommunityplatform.telegrambotadapter.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.swagger.v3.oas.models.Components

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Telegram Bot Adapter API")
                    .version("1.0.0")
                    .description("API для интеграции Telegram бота с микросервисами сообщества")
            )
            .components(
                Components()
                    .addSecuritySchemes(
                    "basicAuth",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic")
                        .description("Введите логин и пароль для доступа к API")
                )
            )
    }
}