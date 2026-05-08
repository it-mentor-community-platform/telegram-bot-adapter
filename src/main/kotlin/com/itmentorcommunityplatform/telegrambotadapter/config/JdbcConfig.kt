package com.itmentorcommunityplatform.telegrambotadapter.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration

@Configuration
class JdbcConfig : AbstractJdbcConfiguration() {
    override fun userConverters(): MutableList<*> {
    return mutableListOf(
        JsonbConverter(),
        JsonbReadingConverter()
    )
}
}