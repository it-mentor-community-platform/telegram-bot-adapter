package com.itmentorcommunityplatform.telegrambotadapter.dto.response

import com.fasterxml.jackson.databind.JsonNode


data class TaskDto(
    val taskType: String,
    val payload: JsonNode,
)
