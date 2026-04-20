package com.itmentorcommunityplatform.telegrambotadapter.listener

import com.itmentorcommunityplatform.telegrambotadapter.dto.event.ProjectCreatedEvent
import com.itmentorcommunityplatform.telegrambotadapter.model.SourceType
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ProjectEventListener {
    private val logger = LoggerFactory.getLogger("ProjectEventListener")
    private val ignore = SourceType.TELEGRAM_BOT.name

    @KafkaListener(
        topics = ["\${spring.kafka.topics.project-created}"],
        groupId = "telegram-bot-adapter-cg",
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