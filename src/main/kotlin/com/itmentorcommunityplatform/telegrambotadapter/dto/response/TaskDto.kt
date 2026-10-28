package com.itmentorcommunityplatform.telegrambotadapter.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRawValue


data class TaskDto(
    @JsonProperty("task_type")
    val taskType: String,
    @JsonRawValue
    val payload: String,
)
