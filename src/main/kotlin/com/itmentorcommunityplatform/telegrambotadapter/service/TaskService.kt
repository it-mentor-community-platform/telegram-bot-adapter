package com.itmentorcommunityplatform.telegrambotadapter.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.itmentorcommunityplatform.telegrambotadapter.dto.response.TaskDto
import com.itmentorcommunityplatform.telegrambotadapter.dto.response.TaskResponseDto
import com.itmentorcommunityplatform.telegrambotadapter.exception.TaskCountOutOfBoundException
import com.itmentorcommunityplatform.telegrambotadapter.repository.TelegramBotTaskRepository
import org.springframework.stereotype.Service

private const val topCountLimit = 10
private const val bottomCountLimit = 1

@Service
class TaskService (
    private val taskRepository: TelegramBotTaskRepository,
    private val objectMapper: ObjectMapper
){
    fun getSentTasks(count: Int): TaskResponseDto{

        if (count !in bottomCountLimit..topCountLimit) {
            throw TaskCountOutOfBoundException("The number of tasks exceeds the allowed limit.")
        }
        val tasks = taskRepository.fetchAndMarkAsSent(count)
        return TaskResponseDto(
            count = tasks.size,
            tasks = tasks.map {
                TaskDto(
                    taskType = it.taskType,
                    payload = it.payload.value)  }
        )
    }
}