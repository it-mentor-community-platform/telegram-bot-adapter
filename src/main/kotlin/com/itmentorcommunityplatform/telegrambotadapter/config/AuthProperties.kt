package com.itmentorcommunityplatform.telegrambotadapter.config

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties("auth")
data class AuthProperties(
    val username: String,
    val password: String,
)
