package com.itmentorcommunityplatform.telegrambotadapter.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("telegram_bot_tasks")
data class TelegramBotTask(
    @Id
    val id: Long? = null,
    val taskType: String,
    val payload: String,
    val createdAt: Long,
    val sent: Boolean = false
)