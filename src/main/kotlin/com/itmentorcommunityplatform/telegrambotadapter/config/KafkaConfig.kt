package com.itmentorcommunityplatform.telegrambotadapter.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.converter.RecordMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter

@Configuration
class KafkaConfig {

    @Bean
    fun messageConverter(objectMapper: ObjectMapper): RecordMessageConverter {
        return StringJsonMessageConverter(objectMapper)
    }
}