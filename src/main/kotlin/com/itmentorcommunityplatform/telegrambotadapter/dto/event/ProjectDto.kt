package com.itmentorcommunityplatform.telegrambotadapter.dto.event

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ProjectDto(
    val id: Long,

    val authorTelegramUserId: Long,

    val githubRepositoryUrl: String,

    val programmingLanguage: String,

    val roadmapProject: String,

    val addedTimestamp: Long
)