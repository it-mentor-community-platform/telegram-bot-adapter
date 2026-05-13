package com.itmentorcommunityplatform.telegrambotadapter.service

import com.itmentorcommunityplatform.telegrambotadapter.dto.response.TaskDto
import com.itmentorcommunityplatform.telegrambotadapter.dto.response.TaskResponseDto
import com.itmentorcommunityplatform.telegrambotadapter.exception.TaskCountException
import com.itmentorcommunityplatform.telegrambotadapter.model.BOTTOM_COUNT_LIMIT
import com.itmentorcommunityplatform.telegrambotadapter.model.TOP_COUNT_LIMIT
import com.itmentorcommunityplatform.telegrambotadapter.repository.TelegramBotTaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService (
    private val taskRepository: TelegramBotTaskRepository
){
    fun getSentedTesks(count: Int): TaskResponseDto{
        if (count < BOTTOM_COUNT_LIMIT || count > TOP_COUNT_LIMIT) {
            throw TaskCountException("The number of tasks exceeds the allowed limit.")
        }
        val tasks = taskRepository.fetchAndMarkAsSent(count)
        return TaskResponseDto(
            count = tasks.size,
            tasks = tasks.map { TaskDto(taskType = it.taskType, payload = it.payload) }
        )
    }
}