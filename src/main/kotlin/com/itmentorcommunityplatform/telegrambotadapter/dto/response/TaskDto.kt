package com.itmentorcommunityplatform.telegrambotadapter.dto.response

import com.fasterxml.jackson.annotation.JsonRawValue
import com.fasterxml.jackson.databind.JsonNode


data class TaskDto(
    val taskType: String,
    @JsonRawValue
    val payload: String,
)
