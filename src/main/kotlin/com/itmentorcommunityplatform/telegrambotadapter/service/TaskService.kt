package com.itmentorcommunityplatform.telegrambotadapter.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.itmentorcommunityplatform.telegrambotadapter.dto.response.TaskDto
import com.itmentorcommunityplatform.telegrambotadapter.dto.response.TaskResponseDto
import com.itmentorcommunityplatform.telegrambotadapter.exception.TaskCountOutOfBoundException
import com.itmentorcommunityplatform.telegrambotadapter.repository.TelegramBotTaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService (
    private val taskRepository: TelegramBotTaskRepository,
    private val objectMapper: ObjectMapper
){
    fun getSentTasks(count: Int): TaskResponseDto{
        val topCountLimit = 10
        val bottomCountLimit = 1
        if (count < bottomCountLimit || count > topCountLimit) {
            throw TaskCountOutOfBoundException("The number of tasks exceeds the allowed limit.")
        }
        val tasks = taskRepository.fetchAndMarkAsSent(count)
        return TaskResponseDto(
            count = tasks.size,
            tasks = tasks.map {
                val payloadNode: JsonNode = objectMapper.readTree(it.payload.value)
                TaskDto(taskType = it.taskType, payload = payloadNode)  }
        )
    }
}