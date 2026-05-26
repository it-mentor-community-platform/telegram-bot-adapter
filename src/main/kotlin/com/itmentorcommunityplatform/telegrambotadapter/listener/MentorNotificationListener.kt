package com.itmentorcommunityplatform.telegrambotadapter.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.itmentorcommunityplatform.telegrambotadapter.dto.event.MentorNotificationEvent
import com.itmentorcommunityplatform.telegrambotadapter.model.JsonbValue
import com.itmentorcommunityplatform.telegrambotadapter.model.TelegramBotTask
import com.itmentorcommunityplatform.telegrambotadapter.repository.TelegramBotTaskRepository
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class MentorNotificationListener(
    private val taskRepository: TelegramBotTaskRepository,
    private val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(MentorNotificationListener::class.java)

    @KafkaListener(
        topics = ["\${spring.kafka.topics.mentor-notification}"],
        groupId = "telegram-bot-adapter-cg",
    )
    fun consume(
        event: MentorNotificationEvent,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String,
    ) {
        val task = TelegramBotTask(
            taskType = topic,
            payload = JsonbValue(objectMapper.writeValueAsString(event)),
            createdAt = Instant.now().epochSecond
        )

        taskRepository.save(task)

        logger.info(
            "Saved mentor notification task for project: roadmapProject={}, mentorsCount={}",
            event.project.roadmapProject,
            event.mentors.size
        )
    }
}