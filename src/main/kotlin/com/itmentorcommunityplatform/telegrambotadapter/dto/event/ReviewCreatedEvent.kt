package com.itmentorcommunityplatform.telegrambotadapter.dto.event

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.itmentorcommunityplatform.telegrambotadapter.model.SourceType

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ReviewCreatedEvent (
    val id: Long,

    val reviewerTelegramUserId: Long,

    val reviewerTelegramProfileUrl: String?,

    val url: String,

    val addedTimestamp: Long,

    val project: ProjectDto,

    val reviewSourceType: SourceType
)