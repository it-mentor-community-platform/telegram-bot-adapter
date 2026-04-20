package com.itmentorcommunityplatform.telegrambotadapter.kafka

import com.itmentorcommunityplatform.telegrambotadapter.dto.event.ProjectCreatedEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ProjectEventListener {
    private val logger = LoggerFactory.getLogger("ProjectEventListener")
    private val ignore = "TELEGRAM_BOT"

    @KafkaListener(
        topics = ["\${spring.kafka.topics.project-created}"],
        groupId = "telegram-bot-adapter-cg",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun consume(event: ProjectCreatedEvent) {
        if (event.projectSourceType.equals(ignore, ignoreCase = true)) {
            logger.info("Ignoring message for ${event.projectSourceType}")
            return
        }

        logger.info("Received project: roadmapProject={}, authorTelegramUserId={}, source={}, created={}",
            event.roadmapProject, event.authorTelegramUserId, event.projectSourceType, event.addedTimestamp)
    }
}