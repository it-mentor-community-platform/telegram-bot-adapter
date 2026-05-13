package com.itmentorcommunityplatform.telegrambotadapter.service

import com.itmentorcommunityplatform.telegrambotadapter.dto.response.TaskDto
import com.itmentorcommunityplatform.telegrambotadapter.dto.response.TaskResponseDto
import com.itmentorcommunityplatform.telegrambotadapter.repository.TelegramBotTaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService (
    private val taskRepository: TelegramBotTaskRepository
){
    fun getSentedTesks(count: Int): TaskResponseDto{
        val tasks = taskRepository.fetchAndMarkAsSent(count)
        return TaskResponseDto(
            count = tasks.size,
            tasks = tasks.map { TaskDto(taskType = it.taskType, payload = it.payload) }
        )
    }
}