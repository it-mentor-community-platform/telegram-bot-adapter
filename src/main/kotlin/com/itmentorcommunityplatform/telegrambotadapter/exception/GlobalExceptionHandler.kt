package com.itmentorcommunityplatform.telegrambotadapter.exception

import com.itmentorcommunityplatform.telegrambotadapter.dto.response.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(TaskCountException::class)
    fun handleTaskCountException(exception: TaskCountException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto(exception.message.toString()))
    }
}