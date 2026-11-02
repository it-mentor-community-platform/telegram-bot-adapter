package com.itmentorcommunityplatform.telegrambotadapter.listener

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Component

@Component
class TelegramBotMetrics(
        private val meterRegistry: MeterRegistry
) {
    fun recordReceivedMessage(
            taskType: String
    ) {
        val counter = meterRegistry.counter(
                "telegram_bot_messages_received",
                "task_type", taskType
        )
        counter.increment();
    }
}