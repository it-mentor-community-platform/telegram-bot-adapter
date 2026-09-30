package com.itmentorcommunityplatform.telegrambotadapter.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.itmentorcommunityplatform.telegrambotadapter.dto.event.ReviewCreatedEvent
import com.itmentorcommunityplatform.telegrambotadapter.model.JsonbValue
import com.itmentorcommunityplatform.telegrambotadapter.model.SourceType
import com.itmentorcommunityplatform.telegrambotadapter.model.TelegramBotTask
import com.itmentorcommunityplatform.telegrambotadapter.repository.TelegramBotTaskRepository
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class ReviewEventListener(
    private val taskRepository: TelegramBotTaskRepository,
    private val objectMapper: ObjectMapper
) {
    private val logger =
        LoggerFactory.getLogger(StudentReviewSubmittedListener::class.java)

    @KafkaListener(
        topics = ["\${spring.kafka.topics.review-created}"],
        groupId = "telegram-bot-adapter-cg"
    )
    fun consume(
        event: ReviewCreatedEvent,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String
    ) {
        if (!validateDataSourceType(event)) return

        val task = TelegramBotTask(
            taskType = topic,
            payload = JsonbValue(objectMapper.writeValueAsString(event)),
            createdAt = System.currentTimeMillis() / 1000
        )

        taskRepository.save(task)

        logger.info(
            "Saved new review notification task: reviewId={}, reviewerTelegramUserId={}",
            event.id,
            event.reviewerTelegramUserId
        )
    }

    private fun validateDataSourceType(event: ReviewCreatedEvent): Boolean {
        return when (event.dataSourceType) {
            SourceType.FRONTEND, SourceType.DATA_IMPORTER -> true
            null -> {
                logger.warn("dataSourceType is null for review id={}", event.id)
                false
            }
            else -> {
                logger.warn(
                    "Unsupported dataSourceType='{}' for review id={}. Allowed: {}, {}",
                    event.dataSourceType, event.id, SourceType.FRONTEND, SourceType.DATA_IMPORTER
                )
                false
            }
        }
    }
}