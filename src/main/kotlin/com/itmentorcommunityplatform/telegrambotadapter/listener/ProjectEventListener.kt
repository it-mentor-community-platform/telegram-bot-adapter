package com.itmentorcommunityplatform.telegrambotadapter.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.itmentorcommunityplatform.telegrambotadapter.dto.event.ProjectCreatedEvent
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
class ProjectEventListener(
    private val taskRepository: TelegramBotTaskRepository,
    private val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger("ProjectEventListener")
    private val ignore = SourceType.TELEGRAM_BOT.name

    @KafkaListener(
        topics = ["\${spring.kafka.topics.project-created}"],
        groupId = "telegram-bot-adapter-cg",
    )
    fun consume(
        event: ProjectCreatedEvent,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String,
    ) {
        if (event.projectSourceType.equals(ignore, ignoreCase = true)) {
            logger.info("Ignoring message for ${event.projectSourceType}")
            return
        }

        val task = TelegramBotTask(
            taskType = topic,
            payload = JsonbValue(objectMapper.writeValueAsString(event)),
            createdAt = System.currentTimeMillis() / 1000
        )

        taskRepository.save(task)

        logger.info(
            "Saved task for project: roadmapProject={}, source={}",
            event.roadmapProject, event.projectSourceType
        )
    }
}