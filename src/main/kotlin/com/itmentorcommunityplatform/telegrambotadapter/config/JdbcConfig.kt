package com.itmentorcommunityplatform.telegrambotadapter.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration

@Configuration
class JdbcConfig : AbstractJdbcConfiguration() {
    override fun userConverters(): MutableList<*> {
    return mutableListOf(
        JsonbWritingConverter(),
        JsonbReadingConverter()
    )
}
}