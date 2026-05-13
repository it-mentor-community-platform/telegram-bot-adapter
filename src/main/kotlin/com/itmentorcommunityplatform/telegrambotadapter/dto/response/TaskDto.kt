package com.itmentorcommunityplatform.telegrambotadapter.dto.response

import com.itmentorcommunityplatform.telegrambotadapter.model.JsonbValue

data class TaskDto(
    val taskType: String,
    val payload: JsonbValue,
)
