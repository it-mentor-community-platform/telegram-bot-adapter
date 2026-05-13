package com.itmentorcommunityplatform.telegrambotadapter.repository

import com.itmentorcommunityplatform.telegrambotadapter.model.TelegramBotTask
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface TelegramBotTaskRepository : CrudRepository<TelegramBotTask, Long> {
    @Transactional
    @Query("""
        WITH selected AS (
            SELECT id FROM telegram_bot_tasks 
            WHERE sent = false 
            LIMIT :count 
            FOR UPDATE SKIP LOCKED
        )
        UPDATE telegram_bot_tasks 
        SET sent = true 
        WHERE id IN (SELECT id FROM selected)
        RETURNING *
    """)
    fun fetchAndMarkAsSent(@Param("count") count: Int): List<TelegramBotTask>
}