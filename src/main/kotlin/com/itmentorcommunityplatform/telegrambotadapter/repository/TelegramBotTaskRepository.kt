package com.itmentorcommunityplatform.telegrambotadapter.repository

import com.itmentorcommunityplatform.telegrambotadapter.model.TelegramBotTask
import org.springframework.data.repository.CrudRepository

interface TelegramBotTaskRepository : CrudRepository<TelegramBotTask, Long> {
}