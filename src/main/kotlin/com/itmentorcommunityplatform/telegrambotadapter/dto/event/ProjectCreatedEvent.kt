package com.itmentorcommunityplatform.telegrambotadapter.dto.event

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ProjectCreatedEvent(

    val authorTelegramUserId: Long,

    val authorTelegramProfileUrl: String,

    val githubRepositoryUrl: String,

    val programmingLanguage: String,

    val roadmapProject: String,

    val addedTimestamp: Long,

    val projectSourceType: String
)