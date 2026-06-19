package com.itmentorcommunityplatform.telegrambotadapter.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.itmentorcommunityplatform.telegrambotadapter.dto.event.StudentReviewSubmittedEvent
import com.itmentorcommunityplatform.telegrambotadapter.model.JsonbValue
import com.itmentorcommunityplatform.telegrambotadapter.model.TelegramBotTask
import com.itmentorcommunityplatform.telegrambotadapter.repository.TelegramBotTaskRepository
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class StudentReviewSubmittedListener(
    private val taskRepository: TelegramBotTaskRepository,
    private val objectMapper: ObjectMapper
) {

    private val logger =
        LoggerFactory.getLogger(StudentReviewSubmittedListener::class.java)

    @KafkaListener(
        topics = ["\${spring.kafka.topics.student-review-submitted}"],
        groupId = "telegram-bot-adapter-cg"
    )
    fun consume(
        event: StudentReviewSubmittedEvent,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String
    ) {
        val task = TelegramBotTask(
            taskType = topic,
            payload = JsonbValue(objectMapper.writeValueAsString(event)),
            createdAt = System.currentTimeMillis() / 1000
        )

        taskRepository.save(task)

        logger.info(
            "Saved student review notification task: reviewId={}, reviewerTelegramUserId={}",
            event.id,
            event.reviewerTelegramUserId
        )
    }
}