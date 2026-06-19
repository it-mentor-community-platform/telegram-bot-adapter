package com.itmentorcommunityplatform.telegrambotadapter.dto.event

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class StudentReviewSubmittedEvent(
    val id: Long,

    val reviewerTelegramUserId: Long,

    val url: String,

    val addedTimestamp: Long,

    val project: ProjectDto
)