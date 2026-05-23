package com.itmentorcommunityplatform.telegrambotadapter.controller

import com.itmentorcommunityplatform.telegrambotadapter.docs.GetTasksDocs
import com.itmentorcommunityplatform.telegrambotadapter.dto.response.TaskResponseDto
import com.itmentorcommunityplatform.telegrambotadapter.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/telegram-bot-adapter")
class TelegramBotAdapterController(
    private val taskService: TaskService
) {

    @GetMapping("/tasks")
    @GetTasksDocs
    fun getTask(@RequestParam count: Int): ResponseEntity<TaskResponseDto> {
        val tasks = taskService.getSentTasks(count)
        return ResponseEntity.ok(tasks)
    }
}