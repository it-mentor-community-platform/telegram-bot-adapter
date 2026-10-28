package com.itmentorcommunityplatform.telegrambotadapter.dto.event

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.itmentorcommunityplatform.telegrambotadapter.model.RoadmapProject
import com.itmentorcommunityplatform.telegrambotadapter.model.SourceType

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ProjectCreatedEvent(

    val authorTelegramUserId: Long,

    val authorTelegramProfileUrl: String,

    val githubRepositoryUrl: String,

    val programmingLanguage: String,

    val roadmapProject: RoadmapProject,

    val addedTimestamp: Long,

    val projectSourceType: SourceType
)